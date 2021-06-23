package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import user.request.BaseUserRequest;
import user.request.SignUpRequest;
import user.response.UserResponse;
import user.service.impl.UserService;

import javax.validation.Valid;

@RestController()
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public UserResponse signup(@Valid @RequestBody SignUpRequest req) {
        return userService.createUser(req);
    }

    @PostMapping("/sign-in")
    public UserResponse signIn(@Valid @RequestBody BaseUserRequest req) {
        return userService.handleSignIn(req);
    }

}
