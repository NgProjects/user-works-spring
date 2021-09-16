package user.dtos;

import lombok.Getter;
import lombok.Setter;
import user.entities.User;

@Getter
@Setter
public class UserDetailDto {

    private String fullName;
    private String email;
    private String phoneNumber;

    public void toUserDetails(User user){
        this.fullName = user.getFullName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
    }
}
