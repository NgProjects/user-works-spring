package user.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtClaimValues {
    private String username;
    private String tokenVerifier;
}
