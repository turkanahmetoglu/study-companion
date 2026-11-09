package service;

import model.Place;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import repository.PlaceRepository;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PlaceRepository placeRepository;

    public DataSeeder(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public void run(String... args) {
        if (placeRepository.count() > 0) {
            return;
        }
        placeRepository.saveAll(List.of(
            new Place("Sunny Corner Cafe", "Kadikoy", "comfy,daylight", "big windows, soft chairs"),
            new Place("Nevada", "Kadikoy", "comfy,daylight", "big windows, soft chairs")
        ));
    }
}