package gud.fun.statemachine.service.data;

import gud.fun.statemachine.dto.city.CityRequestDto;
import gud.fun.statemachine.dto.city.CityResponseDto;
import gud.fun.statemachine.persistance.model.City;
import gud.fun.statemachine.persistance.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public CityResponseDto create(CityRequestDto dto) {
        return toResponseDTO(
                cityRepository.save(
                        toEntity(dto)
                )
        );
    }

    public CityResponseDto getById(UUID id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found"));
        return toResponseDTO(city);
    }

    public List<CityResponseDto> getAll() {
        return cityRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public CityResponseDto update(CityRequestDto dto) {
        City city = cityRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("City not found"));
        city.setName(dto.getName());
        city.setCountryCode(dto.getCountryCode());
        City updatedCity = cityRepository.save(city);
        return toResponseDTO(updatedCity);
    }

    public CityResponseDto delete(UUID id) {
        cityRepository.deleteById(id);
        CityResponseDto responseDto = new CityResponseDto();
        responseDto.setId(id);
        return responseDto;
    }

    public CityResponseDto toResponseDTO(City entity) {
        CityResponseDto cityDto = new CityResponseDto();
        cityDto.setId(entity.getId());
        cityDto.setName(entity.getName());
        cityDto.setCountryCode(entity.getCountryCode());
        return cityDto;
    }

    public City toEntity(CityRequestDto dto) {
        City city = new City();
        city.setId(dto.getId() != null ? dto.getId() : null);
        city.setName(dto.getName());
        city.setCountryCode(dto.getCountryCode());
        return city;
    }
}