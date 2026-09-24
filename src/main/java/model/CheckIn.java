package model;
import java.time.LocalDate;
import jakarta.persistence.*;


@Entity
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String mood;

    @ManyToOne
    private Place suggestedPlace;

    private Boolean isComplete;

    public CheckIn() {
    }

    public CheckIn(LocalDate date, String mood, Place suggestedPlace, Boolean isComplete){
        this.date = date;
        this.mood = mood;
        this.suggestedPlace = suggestedPlace;
        this.isComplete = isComplete;
    }

    // getters and setters
    public Long getId(){
        return id;
    }
    
    public LocalDate getDate(){
        return this.date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public String getMood(){
        return this.mood;
    }

    public void setMood(String mood){
        this.mood = mood;
    }

    public Place getSuggestedPlace() {
    return this.suggestedPlace;
    }

    public void setSuggestedPlace(Place suggestedPlace) {
        this.suggestedPlace = suggestedPlace;
    }

    public Boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }
}
