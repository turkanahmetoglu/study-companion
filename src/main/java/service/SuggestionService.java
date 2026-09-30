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
import java.util.Set;
import java.util.Comparator;

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
		Set<Long> ids = new HashSet<>();
        for(CheckIn checkin: checkInRepository.findAll()){
            if(checkin.getIsComplete() != null && checkin.getSuggestedPlace() !=null && checkin.getIsComplete()){
                ids.add(checkin.getSuggestedPlace().getId());
            }
        }
        List<Place> notVisited = new ArrayList<>();
        for(Place place: placeRepository.findAll()){
            if(!ids.contains(place.getId())){
                notVisited.add(place);
            }
        }

        return notVisited;
    }

    public Place suggest(String mood){
        if(mood == null) return null;

        String tagForMood = tagForMood(mood);
        if(tagForMood == null) return null;

        List<Place> candidate = new ArrayList<>();

        if(mood.equalsIgnoreCase("motivated")){
            for (Place place: findUnvisited()){
                if(place.getTags() != null && place.getTags().contains(tagForMood)){
                    candidate.add(place);
                }
            }
        }
        else{
            candidate = findByTag(tagForMood);
        }

        return pickRandom(candidate);
    }

    public List<CheckIn> sortedCheckIns() {
        List<CheckIn> checkins = checkInRepository.findAll();
        checkins.sort(Comparator.comparing(CheckIn::getDate));
        return checkins;
    }

    public int calculateWarmth() {
        int warmth = 0;

        for (CheckIn checkin : sortedCheckIns()) {
            if (checkin.getIsComplete() != null && checkin.getIsComplete()) {
                warmth++;
            } else {
                warmth--;
                if (warmth < 0) warmth = 0;
            }
        }

        return warmth;
    }

    public int currentStreak() {
        List<CheckIn> checkins = sortedCheckIns();
        int streak = 0;

        for (int i = checkins.size() - 1; i >= 0; i--) {
            CheckIn checkin = checkins.get(i);
            if (checkin.getIsComplete() != null && checkin.getIsComplete()) {
                streak++;
            } else {
                break;
            }
        }

        return streak;
    }
}

