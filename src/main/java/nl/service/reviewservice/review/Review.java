package nl.service.reviewservice.review;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Random;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    String text;
    int rating;
    String userName;
    String movieName;
    public void setText(String text) {
        this.text = text;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}
