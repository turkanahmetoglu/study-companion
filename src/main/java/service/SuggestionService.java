package service;

// importing my classes
import repository.PlaceRepository;
import repository.CheckInRepository;
import model.Place;
import model.CheckIn;

// importing service
import org.springframework.stereotype.Service;

// importing java's classes
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashSet;

@Service
public class SuggestionService {
    private final PlaceRepository placeRepository;
    private final CheckInRepository checkInRepository;

    public SuggestionService(PlaceRepository placeRepository, CheckInRepository checkInRepository){
        this.checkInRepository = checkInRepository;
        this.placeRepository = placeRepository;
    }

    public List<Place> findByTag(String tag) {
        List<Place> matches =  new ArrayList<>();
        
        for (Place place : placeRepository.findAll()){
            if(place.getTags() !=null && place.getTags().contains(tag)){
                matches.add(place);
            }
        }
        
        return matches;
    }
    public String tagForMood(String mood) {
        switch(mood.toLowerCase()){
            case "happy":
                return "lively";
            case "tired":
                return "comfy";
            case "sad":
                return "daylight";
            case "motivated":
                return "focus"; 
            default:
                return null;
        }
    }
    public Place pickRandom(List<Place> places){
        if(places.isEmpty()) return null;

        int randNum = new Random().nextInt(places.size());
        return places.get(randNum);
    }
    public List<Place> findUnvisited(){
		HashSet<Long> ids = new HashSet<>();
        for(CheckIn checkin: checkInRepository.findAll()){
            if(checkin.getIsComplete() != null && checkin.getIsComplete()){
                ids.add(checkin.getId());
            }
        }

        return null;
    }
}
