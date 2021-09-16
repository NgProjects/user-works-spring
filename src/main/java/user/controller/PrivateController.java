package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import user.response.UserListResponse;
import user.service.impl.UserService;

@RestController
@RequestMapping(path = "private")
public class PrivateController {

    UserService userService;

    @Autowired
    public PrivateController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("get-user-list")
    public UserListResponse getUserList(@RequestParam(name = "start") int start, @RequestParam(name = "size") int size) {
        return userService.getUserList(start,size);
    }
}
