package user.response;

import lombok.Getter;
import lombok.Setter;
import user.dtos.UserDetailDto;
import user.entities.User;

@Getter
@Setter
public class UserResponse extends BaseResponse {

    private UserDetailDto userDetail;

    public void toUserDetails(User user){
        this.userDetail = new UserDetailDto();
        this.userDetail.toUserDetails(user);
    }
}
