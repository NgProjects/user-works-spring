package user.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import user.constants.UserServiceConstants;
import user.dtos.KafkaUserMessage;

@Component
public class KafkaListeners {

    @KafkaListener(topics = UserServiceConstants.KAFKA_TOPIC_NAME,groupId = "group1", containerFactory = "kafkaUserMessageConsumerFactory")
    void listener(KafkaUserMessage data){
        if(data == null || data.getUserDetails() == null){
            return;
        }
        //do anything with the user details
        System.out.println("received: " + data.getUserDetails().getEmail()); //just to see outcome
    }
}
