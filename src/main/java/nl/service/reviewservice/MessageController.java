package nl.service.reviewservice;
import nl.service.reviewservice.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
@EnableScheduling
@Controller
public class MessageController {
    private final String movieEndpointURL ="/topic/review";
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Review send(Review message) {
        message.setText("Hello World!");
        System.out.println("got message!!!!!!");
        return message;
    }

}