package user.response;

import lombok.Getter;
import lombok.Setter;
import user.enums.ResponseCode;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -5644370827082193879L;

    private Integer responseCode = ResponseCode.ERROR.getCode();
    private String description;

    public void configureResponse(ResponseCode responseCode){
        this.responseCode = responseCode.getCode();
        this.description = responseCode.getDescription();
    }
}
