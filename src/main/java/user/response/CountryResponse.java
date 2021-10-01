package user.response;

import lombok.Getter;
import lombok.Setter;
import user.dtos.CountryDto;

import java.util.List;

@Getter
@Setter
public class CountryResponse extends BaseResponse {

    private static final long serialVersionUID = -161467996536492756L;

    private List<CountryDto> countries;
}
