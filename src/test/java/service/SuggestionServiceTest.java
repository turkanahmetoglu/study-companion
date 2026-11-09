package service;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import model.Place;
import model.CheckIn;
import repository.CheckInRepository;
import repository.PlaceRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class SuggestionServiceTest {

    @Test
    void tagForMood_returnsCorrectTag() {
        SuggestionService service = new SuggestionService(null, null);

        assertEquals("comfy", service.tagForMood("tired"));
        assertEquals("lively", service.tagForMood("happy"));
        assertEquals(null, service.tagForMood("angry"));
    }

    @Test
    void pickRandom_emptyList_returnsNull() {
        SuggestionService service = new SuggestionService(null, null);

        Place result = service.pickRandom(new ArrayList<>());

        assertNull(result);
    }

    @Test
    void pickRandom_onePlaceList_returnsOnePlace(){
        Place place = new Place("Cafe", "Kadikoy", "cozy", "");
        SuggestionService service = new SuggestionService(null, null);

        Place result = service.pickRandom(List.of(place));

        assertEquals(place, result);
    }

    @Test
    void pickRandom_severalPlacesList_returnsPlaceInList(){
        List<Place> places = List.of(
        new Place("Cafe A", "Kadikoy", "cozy", ""),
        new Place("Cafe B", "Besiktas", "focus", ""),
        new Place("Cafe C", "Sisli", "lively", "")
        );

        SuggestionService service = new SuggestionService(null, null);

        for(int i=0; i < 20; i++){
            Place result = service.pickRandom(places);
            
            assertTrue(places.contains(result));
        }
    }

    @Test
    void tagForMood_caseInsensitive(){
        SuggestionService service = new SuggestionService(null, null);

        assertEquals("comfy", service.tagForMood("TIRED"));
        assertEquals("lively", service.tagForMood("HAPPY"));
        assertEquals("lively", service.tagForMood("Happy"));

    }

    @Test
    void findByTag_returnsOnlyMatchingPlaces() {
        PlaceRepository placeRepository = mock(PlaceRepository.class);
        CheckInRepository checkInRepository = mock(CheckInRepository.class);

        Place cozy = new Place("A", "Kadikoy", "cozy,quiet", "");
        Place wifi = new Place("B", "Besiktas", "wifi", "");
        when(placeRepository.findAll()).thenReturn(List.of(cozy, wifi));

        SuggestionService service = new SuggestionService(placeRepository, checkInRepository);

        List<Place> result = service.findByTag("cozy");

        assertEquals(1, result.size());
        assertEquals(cozy, result.get(0));
    }

    @Test
    void currentStreak_returnsCurrentStreak(){
        CheckInRepository checkInRepository = mock(CheckInRepository.class);
        PlaceRepository placeRepository = mock(PlaceRepository.class);

        // CheckIn build
        CheckIn c1 = checkIn(1, true);
        CheckIn c2 = checkIn(2, true);
        CheckIn c3 = checkIn(3, false);
        CheckIn c4 = checkIn(4, true);
        CheckIn c5 = checkIn(5, true);
        CheckIn c6 = checkIn(6, true);

        when(checkInRepository.findAll()).thenReturn(new ArrayList<>(List.of(c1, c2, c3, c4, c5, c6)));

        SuggestionService service = new SuggestionService(placeRepository, checkInRepository);
        int result = service.currentStreak();

        assertEquals(3, result);
    }

    private CheckIn checkIn(int day, boolean complete) {
        CheckIn c = new CheckIn();
        c.setDate(LocalDate.of(2026, 11, day));
        c.setIsComplete(complete);
        return c;
    }
}