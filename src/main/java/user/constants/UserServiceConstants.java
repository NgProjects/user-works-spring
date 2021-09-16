package user.constants;

public class UserServiceConstants {
    public static String TOKEN_BEARER_PREFIX = "Bearer ";
    public static final String JWT_SECRET = "secretkey";
    public static final String JWT_ISSUER = "user-service";
    public static final String[] SWAGGER_DOC_URLS = {
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };
}
