package controller;

import model.CheckIn;
import repository.CheckInRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkins")
public class CheckInController {

    private final CheckInRepository checkInRepository;

    public CheckInController(CheckInRepository checkInRepository){
        this.checkInRepository = checkInRepository;
    }
    
    @GetMapping
    public List<CheckIn> getAllCheckIns(){
        return  checkInRepository.findAll();
    }

    @PostMapping
    public CheckIn addCheckIn(@RequestBody CheckIn checkin){
        return checkInRepository.save(checkin);
    }

}
