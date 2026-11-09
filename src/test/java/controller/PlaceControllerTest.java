package controller;

import model.Place;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import repository.PlaceRepository;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PlaceControllerTest {

    @Test
    void getAllPlaces_returns200AndList() throws Exception {
        PlaceRepository placeRepository = mock(PlaceRepository.class);
        when(placeRepository.findAll()).thenReturn(List.of(new Place("Cafe A", "Kadikoy", "cozy", "")));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new PlaceController(placeRepository)).build();

        mockMvc.perform(get("/places"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].name").value("Cafe A"));
    }

    @Test
    void postEmptyName_returns400AndMessage() throws Exception{
        PlaceRepository placeRepository = mock(PlaceRepository.class);
        when(placeRepository.findAll()).thenReturn(List.of(new Place("Cafe A", "Kadikoy", "cozy", "")));

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new PlaceController(placeRepository)).setControllerAdvice(new GlobalExceptionHandler()).build();
        
        mockMvc.perform(post("/places")
               .contentType(MediaType.APPLICATION_JSON)
               .content("{\"name\":\"\",\"area\":\"Kadikoy\"}"))
       .andExpect(status().isBadRequest())
       .andExpect(jsonPath("$.name").value("Place name must not be empty."));                   

    }
}