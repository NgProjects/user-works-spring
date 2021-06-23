package user.response;

import lombok.Getter;
import lombok.Setter;
import user.entities.User;

@Getter
@Setter
public class UserResponse extends BaseResponse {

    private String fullName;
    private String email;
    private String phoneNumber;

    public void setUserDetails(User user){
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
