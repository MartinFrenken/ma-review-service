package nl.service.reviewservice;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Random;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Review {
    String text;
    int rating;
    int userID;
    public void setText(String text) {
        this.text = text;
    }
    public Review(){
        Random random = new Random();
        rating= random.nextInt(5)+1;
        userID= random.nextInt(323);
    }
}
