package user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignUpRequest extends BaseUserRequest {

    @NotBlank(message = "Full Name must be provided")
    private String fullName;

    @NotBlank(message = "Phone Number must be provided")
    private String phoneNumber;

    @NotBlank(message = "Country must be provided")
    private Long countryId;

}
