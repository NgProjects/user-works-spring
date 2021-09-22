package user.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CountryDto implements Serializable {

    private static final long serialVersionUID = 4957963382301713605L;

    private long countryId;
    private String countryName;
}
