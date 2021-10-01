package user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import user.dtos.CountryDto;
import user.entities.Country;
import user.enums.ResponseCode;
import user.repositories.CountryRepository;
import user.response.CountryResponse;
import user.service.interfaces.ICountryService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@CacheConfig(cacheNames={"country"})
public class CountryService implements ICountryService {

    CountryRepository countryRepository;

    @Autowired
    CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryDto> getCountries() {

        List<Country> countries = this.countryRepository.findAll();

        List<CountryDto> result;
        if(countries.isEmpty()) {
            result = Collections.emptyList();
        }else {
            result = countries.stream().map(this::getCountryDto).collect(Collectors.toList());
        }
        log.info("Calling DB to retrieve countries");
        return result;
    }

    /**
     *
     * @param country
     * @return
     */
    private CountryDto getCountryDto(Country country) {

        CountryDto countryDto = new CountryDto();

        countryDto.setCountryId(country.getId());
        countryDto.setCountryName(country.getName());

        return countryDto;
    }

    /**
     *
     * @return
     */
    @Cacheable(key = "#root.methodName" , value = "countryResponse")
    public CountryResponse getCountryResponse() {
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.configureResponse(ResponseCode.SUCCESS);
        countryResponse.setCountries(getCountries());
        return countryResponse;
    }
}
