package user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import user.dtos.UserDetailDto;
import user.entities.User;
import user.enums.ResponseCode;
import user.pojo.LoginResults;
import user.repositories.UserRepository;
import user.request.BaseUserRequest;
import user.request.SignUpRequest;
import user.response.UserListResponse;
import user.response.UserResponse;
import user.security.JwtTokenUtility;
import user.service.interfaces.IUserService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    UserRepository userRepository;
    JwtTokenUtility jwtTokenUtility;

    @Autowired
    public UserService(UserRepository userRepository,
                       JwtTokenUtility jwtTokenUtility) {
        this.userRepository = userRepository;
        this.jwtTokenUtility = jwtTokenUtility;
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
       // user.setCountry();
        user.setTokenVerifier(String.valueOf(System.currentTimeMillis()));

        userRepository.save(user);

        response.toUserDetails(user);
        response.configureResponse(ResponseCode.SUCCESS);

        return response;
    }

    @Override
    public LoginResults handleSignIn(BaseUserRequest request) {

        LoginResults loginResults = new LoginResults();

        UserResponse userResponse = new UserResponse();
        userResponse.setDescription("Invalid username provided");

        User user = userRepository.findByEmail(request.getEmail());
        if(user == null || !user.isValidPassword(request.getPassword())){
            loginResults.setUserResponse(userResponse);
            return loginResults;
        }

        userResponse.toUserDetails(user);
        userResponse.configureResponse(ResponseCode.SUCCESS);

        loginResults.setAuthorization(jwtTokenUtility.generateAuthorizationToken(user));
        loginResults.setUserResponse(userResponse);
        return loginResults;
    }

    @Override
    public UserListResponse getUserList(int start, int size) {

        UserListResponse userListResponse = new UserListResponse();

        Pageable pageRequest = PageRequest.of(start, size, Sort.by("dateCreated").descending());
        Page<User> paginatedResult = this.userRepository.findAll(pageRequest);

        List<User> users = paginatedResult.getContent();

        userListResponse.configureResponse(ResponseCode.SUCCESS);
        userListResponse.setTotalCount(paginatedResult.getTotalElements());
        userListResponse.setUsers(getUserDetails(users));

        return userListResponse;
    }

    /**
     *
     * @param users
     * @return
     */
    private List<UserDetailDto> getUserDetails(List<User> users) {

        List<UserDetailDto> result;

        if(users == null || users.isEmpty()) {
            result = Collections.emptyList();
        }else {
            result = users.stream().map(this::getUserDetailsDto).collect(Collectors.toList());
        }
        return result;
    }

    /**
     *
     * @param user
     * @return
     */
    private UserDetailDto getUserDetailsDto(User user) {
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.toUserDetails(user);
        return userDetailDto;
    }
}
