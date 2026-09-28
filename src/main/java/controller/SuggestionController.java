package controller;

import org.springframework.web.bind.annotation.*;

import service.SuggestionService;
import model.Place;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService){
        this.suggestionService = suggestionService;
    }

    @GetMapping
    public Place suggestPlace(@RequestParam String mood){
        return suggestionService.suggest(mood);
    }

}
