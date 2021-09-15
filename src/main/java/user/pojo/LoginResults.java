package user.pojo;

import lombok.Getter;
import lombok.Setter;
import user.response.UserResponse;

@Getter
@Setter
public class LoginResults {
    private UserResponse userResponse;
    private String authorization;
}
