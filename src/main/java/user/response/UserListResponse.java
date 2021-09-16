package user.response;


import lombok.Getter;
import lombok.Setter;
import user.dtos.UserDetailDto;

import java.util.List;

@Getter
@Setter
public class UserListResponse extends BaseResponse {
    List<UserDetailDto> users;
    long totalCount;
}
