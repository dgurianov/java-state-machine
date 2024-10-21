package gud.fun.statemachine.persistance.repository;

import gud.fun.statemachine.persistance.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    List<City> findAllByCountryCode(String countryCode);
}