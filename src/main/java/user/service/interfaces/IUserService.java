package user.service.interfaces;

import user.pojo.LoginResults;
import user.request.BaseUserRequest;
import user.request.SignUpRequest;
import user.response.UserListResponse;
import user.response.UserResponse;

public interface IUserService {
    UserResponse createUser(SignUpRequest request);
    LoginResults handleSignIn(BaseUserRequest request);
    UserListResponse getUserList(int start, int size);
}
