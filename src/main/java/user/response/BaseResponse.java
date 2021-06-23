package user.response;

import lombok.Getter;
import lombok.Setter;
import user.enums.ResponseCode;

@Getter
@Setter
public class BaseResponse {

    private Integer responseCode = ResponseCode.ERROR.getCode();
    private String description;

    public void configureResponse(ResponseCode responseCode){
        this.responseCode = responseCode.getCode();
        this.description = responseCode.getDescription();
    }
}
