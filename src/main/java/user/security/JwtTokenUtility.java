package user.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import user.constants.UserServiceConstants;
import user.entities.User;
import user.pojo.JwtClaimValues;

import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtility {

    /**
     *
     * @param user
     * @return
     */
    public String generateAuthorizationToken(User user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getTokenVerifier(), user.getUsername()))
                .setIssuer(UserServiceConstants.JWT_ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, UserServiceConstants.JWT_SECRET)
                .compact();
    }

    /**
     *
     * @param token
     * @return
     */
    public JwtClaimValues getClaimValues(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(UserServiceConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        String[] splitToken = claims.getSubject().split(",");

        JwtClaimValues jwtClaimValues = new JwtClaimValues();
        jwtClaimValues.setTokenVerifier(splitToken[0]);
        jwtClaimValues.setUsername(splitToken[1]);
        return jwtClaimValues;
    }

    /**
     *
     * @param token
     * @return
     */
    public boolean validToken(String token) {
        try {
            Jwts.parser().setSigningKey(UserServiceConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

}
