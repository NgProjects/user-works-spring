package user.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import user.entities.User;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void testFindByEmail(){

        String email = "test@email.com";

        User user = getTestUser(email,"1908298939");
        userRepository.save(user);

        User actualValue = userRepository.findByEmail(email);

        Assertions.assertNotNull(actualValue);
        assertThat(actualValue.getEmail()).isEqualTo(email);

        User secondActualValue = userRepository.findByEmail("unknown@email.com");
        Assertions.assertNull(secondActualValue);
    }

    @Test
    public void testFindByPhoneNumber(){
        String phoneNumber = "1908298939";

        User user = getTestUser("test@email.com",phoneNumber);
        userRepository.save(user);

        User actualValue = userRepository.findByPhoneNumber(phoneNumber);

        Assertions.assertNotNull(actualValue);
        assertThat(actualValue.getPhoneNumber()).isEqualTo(phoneNumber);

        User secondActualValue = userRepository.findByPhoneNumber("19082989397");
        Assertions.assertNull(secondActualValue);
    }

    private User getTestUser(String email, String phoneNumber) {
        User user = new User();
        user.setEmail(email);
        user.setPassword("test");
        user.setFullName("fullname");
        user.setPhoneNumber(phoneNumber);
        user.setTokenVerifier(String.valueOf(System.currentTimeMillis()));
        return user;
    }

}