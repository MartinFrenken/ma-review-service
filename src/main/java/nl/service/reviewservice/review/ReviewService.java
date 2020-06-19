package nl.service.reviewservice.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    public List<Review> getByMovieName(String name){
        List<Review> allReviews;
        allReviews=reviewRepository.findAll();
        List<Review> returnReviews = new ArrayList<>();
        for (Review review:allReviews) {
            if(review.movieName!=null)
            if(review.movieName.equals(name)) {
                returnReviews.add(review);
            }
        }
        return returnReviews;
    }
    public void deleteAllReviews() {
        reviewRepository.deleteAll();
    }
}
