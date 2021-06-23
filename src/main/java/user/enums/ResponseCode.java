package user.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(0,"Successful"),
    ERROR(-1,"An error has occured"),

    ;

    private int code;
    private String description;

    ResponseCode(int responseCode, String responseDescription){
        this.code = responseCode;
        this.description = responseDescription;
    }

}
