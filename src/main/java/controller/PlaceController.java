package controller;

import model.Place;
import repository.PlaceRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/places")
public class PlaceController {

    private final PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @GetMapping
    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }


    @PostMapping
    public Place addPlace(@Valid @RequestBody Place place) {
        return placeRepository.save(place);
    }
}