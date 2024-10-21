package gud.fun.statemachine.util.generator;

import com.neovisionaries.i18n.CountryCode;
import gud.fun.statemachine.persistance.model.Country;
import gud.fun.statemachine.persistance.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CountryGenerator implements JunkDataGenerator<Country, CountryCode> {

    private Random random = new Random();

    @Autowired
    private CityRepository cityRepository;


    @Override
    public Country generateRandom() {

        CountryCode countryCode = null;
        while(countryCode == null){
            countryCode = CountryCode.getByCode(random.nextInt(999));
        }
        Country countryEntity = new Country(countryCode);
        countryEntity.getCities().addAll(cityRepository.findAllByCountryCode(countryCode.getAlpha3()));

        return countryEntity;
    }

    @Override
    public Country generateRandomByCriteria(CountryCode criteria) {
        return new Country(criteria);
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom().getName();
    }

    @Override
    public String generateRandomAsStringByCriteria(CountryCode criteria) {
        return generateRandomByCriteria(criteria).getName();
    }


}
