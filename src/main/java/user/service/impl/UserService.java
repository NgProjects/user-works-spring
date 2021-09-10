package user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.entities.User;
import user.enums.ResponseCode;
import user.repositories.UserRepository;
import user.request.BaseUserRequest;
import user.request.SignUpRequest;
import user.response.UserResponse;
import user.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse createUser(SignUpRequest request) {

        UserResponse response = new UserResponse();

        if(userRepository.findByEmail(request.getEmail()) != null){
            response.setDescription("User with email already exists");
            return response;
        }

        if(userRepository.findByPhoneNumber(request.getPhoneNumber()) != null){
            response.setDescription("User with phone number already exists");
            return response;
        }

        User user = new User();

        //Password harsh is generated inside the setter
        //You will need to write a better algorithm to generate your password harsh
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(user);

        response.toUserDetails(user);
        response.configureResponse(ResponseCode.SUCCESS);

        return response;
    }

    @Override
    public UserResponse handleSignIn(BaseUserRequest request) {

        UserResponse response = new UserResponse();
        response.setDescription("Invalid username provided");

        User user = userRepository.findByEmail(request.getEmail());
        if(user == null || !user.isValidPassword(request.getPassword())){
            return response;
        }

        response.toUserDetails(user);
        response.configureResponse(ResponseCode.SUCCESS);
        return response;
    }
}
