package user.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import user.constants.UserServiceConstants;

@Configuration
public class TopicConfig {

    public NewTopic userWorksTopic(){
        return TopicBuilder.name(UserServiceConstants.KAFKA_TOPIC_NAME).build();
    }
}
