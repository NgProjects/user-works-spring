package user.filters;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import user.constants.UserServiceConstants;
import user.pojo.JwtClaimValues;
import user.repositories.UserRepository;
import user.security.JwtTokenUtility;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.List.of;
import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class JwtValidationFilter extends OncePerRequestFilter {

    private JwtTokenUtility jwtTokenUtility;
    private UserRepository userRepository;


    @Autowired
    public JwtValidationFilter(UserRepository userRepository,
                               JwtTokenUtility jwtTokenUtility) {
        this.userRepository = userRepository;
        this.jwtTokenUtility = jwtTokenUtility;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(UserServiceConstants.TOKEN_BEARER_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // Get jwt token and validate
        final String token = authHeader.split(" ")[1].trim();
        if (!jwtTokenUtility.validToken(token)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        JwtClaimValues jwtClaimValues = jwtTokenUtility.getClaimValues(token);
        // Get user identity and set it on the spring security context
        UserDetails userDetails = userRepository.findByEmail(jwtClaimValues.getUsername());

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                ofNullable(userDetails).map(UserDetails::getAuthorities).orElse(of())
        );

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

}
