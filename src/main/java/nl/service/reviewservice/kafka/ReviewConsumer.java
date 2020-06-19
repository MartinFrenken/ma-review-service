package nl.service.reviewservice.kafka;

import com.google.gson.Gson;
import nl.service.reviewservice.MessageController;
import nl.service.reviewservice.review.PostReview;
import nl.service.reviewservice.review.Review;
import nl.service.reviewservice.review.ReviewRepository;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReviewConsumer {
    private final String movieEndpointURL ="/topic/review";
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private SimpMessagingTemplate template;

    private final Logger logger = LoggerFactory.getLogger(ReviewProducer.class);
    private Gson gson = new Gson();
    @KafkaListener(topics = "reviews", groupId = "group_id")
    public void consume(String message) throws IOException {
        PostReview postReview = gson.fromJson(message, PostReview.class);
        CloseableHttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.get()
                .setUri("https://dev-on-fhj05.eu.auth0.com/userinfo")
                .setHeader("Authorization",postReview.getUserToken())
                .build();
       CloseableHttpResponse response = client.execute(request);
         HttpEntity user = response.getEntity();
         String username;
        if (user != null) {
            String retSrc = EntityUtils.toString(user);
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(retSrc);
                username = jsonObject.getString("name");
                Review review = new Review();
                review.setText(postReview.getText());
                review.setRating(postReview.getRating());
                review.setUserName(username);
                review.setMovieName(postReview.getMovieName());
                reviewRepository.save(review);
                writeReview(review);
            } catch (JSONException err) {
                System.out.println("Json parse error!");
            }


        }
    }
    public void writeReview(Review inputReview){

        System.out.println("sent review!");
        template.convertAndSend(movieEndpointURL,inputReview);
    }
}