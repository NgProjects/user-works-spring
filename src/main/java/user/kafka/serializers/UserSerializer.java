package user.kafka.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.data.redis.serializer.SerializationException;
import user.dtos.KafkaUserMessage;

import java.util.Map;

//Or we can use JsonSerializer
public class UserSerializer implements Serializer<KafkaUserMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, KafkaUserMessage kafkaUserMessage) {
        try {
            if (kafkaUserMessage == null){
                return null;
            }
            return objectMapper.writeValueAsBytes(kafkaUserMessage);
        } catch (Exception e) {
            throw new SerializationException("Error while serializing kafkaUserMessage");
        }
    }

    @Override
    public void close() {
        //Serializer.super.close();
    }
}
