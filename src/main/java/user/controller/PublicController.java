package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.constants.UserServiceConstants;
import user.pojo.LoginResults;
import user.request.BaseUserRequest;
import user.request.SignUpRequest;
import user.response.UserResponse;
import user.service.impl.UserService;

import javax.validation.Valid;

@RestController()
@RequestMapping(path = "public")
public class PublicController {

    UserService userService;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("sign-up")
    public UserResponse signup(@Valid @RequestBody SignUpRequest req) {
        return userService.createUser(req);
    }

    @PostMapping("sign-in")
    public ResponseEntity<UserResponse> signIn(@Valid @RequestBody BaseUserRequest req) {
        LoginResults loginResult = userService.handleSignIn(req);

        String bearer = UserServiceConstants.TOKEN_BEARER_PREFIX + loginResult.getAuthorization();
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, bearer)
                .body(loginResult.getUserResponse());
    }

}
