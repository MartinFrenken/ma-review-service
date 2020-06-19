package nl.service.reviewservice;
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
    @Scheduled(fixedRate = 3000)
    public void writeReview(){
        Review review = new Review();
        System.out.println("sent review!");
        review.setText("Best wel goede film, alleen einde was een beetje slecht");
        template.convertAndSend(movieEndpointURL,review);
    }
}