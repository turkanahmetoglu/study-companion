package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuggestionServiceTest {

    @Test
    void tagForMood_returnsCorrectTag() {
        SuggestionService service = new SuggestionService(null, null);

        assertEquals("comfy", service.tagForMood("tired"));
        assertEquals("lively", service.tagForMood("happy"));
        assertEquals(null, service.tagForMood("angry"));
    }
}