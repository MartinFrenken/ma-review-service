package nl.service.reviewservice.review;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Random;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PostReview {
    String text;
    int rating;
    String movieName;
    String userToken;
    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
    public String getText() {
        return text;
    }

    public int getRating() {
        return rating;
    }

    public String getMovieName() {
        return movieName;
    }


}
