package user.kafka.serializers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.data.redis.serializer.SerializationException;
import user.dtos.KafkaUserMessage;

import java.nio.charset.StandardCharsets;
import java.util.Map;

//Or we can use JsonDeserializer
public class UserDeserializer implements Deserializer<KafkaUserMessage> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public KafkaUserMessage deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null){
                return null;
            }
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), KafkaUserMessage.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to KafkaUserMessage");
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
