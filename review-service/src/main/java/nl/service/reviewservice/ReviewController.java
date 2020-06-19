package nl.service.reviewservice;

import nl.service.reviewservice.kafka.ReviewProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private final Producer producer;

    @Autowired
    ReviewController(Producer producer) {
        this.producer = producer;
    }
    @GetMapping("/api/resource")
    public String getProtectedResource(){
        producer.sendMessage("Jarno trek een bak!");
        System.out.println("f");
        return "Protected Resource";
    }
}
