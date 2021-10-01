package user.service.interfaces;

import user.dtos.CountryDto;
import user.entities.Country;

import java.util.List;

public interface ICountryService {
    List<CountryDto> getCountries();
}
