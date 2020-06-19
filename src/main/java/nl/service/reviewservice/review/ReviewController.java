package nl.service.reviewservice.review;

import com.google.gson.Gson;
import nl.service.reviewservice.httpexception.ForbiddenException;
import nl.service.reviewservice.kafka.ReviewProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("review-api")
@RestController
public class ReviewController {
    private final Gson gson = new Gson();
    private final ReviewProducer reviewProducer;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewController(ReviewProducer reviewProducer) {
        this.reviewProducer = reviewProducer;
    }
    @GetMapping("/get/reviews/{name}")
    public List<Review> getProtectedResource(@PathVariable String name){

            return reviewService.getByMovieName(name);
    }
    @PostMapping("/post/review")
    public String postNewReview(@RequestBody PostReview review, @RequestHeader("authorization") String token){
        if(token.length()<1){
            //not secure
            throw new ForbiddenException();
        }
        review.setUserToken(token);
        reviewProducer.sendMessage(gson.toJson(review));
        return "succes";
    }
    @GetMapping("/delete")
    public void deleteData(){
         reviewService.deleteAllReviews();
    }
}
