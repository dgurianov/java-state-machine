package gud.fun.statemachine.controller;

import gud.fun.statemachine.configuration.Endpoints;
import gud.fun.statemachine.dto.city.CityRequestDto;
import gud.fun.statemachine.dto.city.CityResponseDto;
import gud.fun.statemachine.persistance.model.City;
import gud.fun.statemachine.service.data.JunkDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value=Endpoints.CITY)
public class CityController {

    @Autowired
    private JunkDataService<CityRequestDto,CityResponseDto, City> cityService;

    @GetMapping(value = "/{id}",produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityResponseDto> getOneById(@PathVariable UUID id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<CityResponseDto>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<CityResponseDto> createOrUpdate(@RequestBody CityRequestDto dto) {
        return ResponseEntity.ok(cityService.update(dto));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CityResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(cityService.delete(id));
    }
}