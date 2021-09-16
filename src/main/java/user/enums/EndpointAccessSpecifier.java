package user.enums;

import lombok.Getter;

@Getter
public enum EndpointAccessSpecifier {

    PUBLIC_ENDPOINT("/**/public/**");

    private final String pathPattern;

    EndpointAccessSpecifier(String pathPattern){
        this.pathPattern = pathPattern;
    }

}
