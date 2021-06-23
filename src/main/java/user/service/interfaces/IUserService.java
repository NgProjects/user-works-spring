package user.service.interfaces;

import user.entities.User;
import user.request.BaseUserRequest;
import user.request.SignUpRequest;
import user.response.UserResponse;

public interface IUserService {
    UserResponse createUser(SignUpRequest request);
    UserResponse handleSignIn(BaseUserRequest request);
}
