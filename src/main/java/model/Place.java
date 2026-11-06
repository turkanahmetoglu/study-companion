package model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Place name must not be empty.")
    private String name;

    @NotBlank(message = "Area must not be empty.")
    private String area;
    private String tags;
    private String notes;

    public Place() {
    }

    public Place(String name, String area, String tags, String notes) {
        this.name = name;
        this.area = area;
        this.tags = tags;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}