package com.csiszer.my_shop.service.user;

import com.csiszer.my_shop.dto.UserDto;
import com.csiszer.my_shop.exceptions.AlreadyExistsExcepptions;
import com.csiszer.my_shop.exceptions.ResourceNotFoundException;
import com.csiszer.my_shop.model.User;
import com.csiszer.my_shop.repository.UserRepository;
import com.csiszer.my_shop.request.CreateUserRequest;
import com.csiszer.my_shop.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User createUser(CreateUserRequest createuserRequest) {
        return Optional.of(createuserRequest)
                .filter(user -> !userRepository.existsByEmail(createuserRequest.getEmil()))
                .map(request -> {
                    User user = new User();
                    user.setEmail(request.getEmil());
                    user.setPassword(request.getPassword());
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return userRepository.save(user);
                }).orElseThrow( () -> new AlreadyExistsExcepptions(createuserRequest.getEmil() + " user already exist"));
    }

    @Override
    public User updateUser(UserUpdateRequest userUpdateRequest, Long userId) {
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setFirstName(userUpdateRequest.getFirstName());
                    existingUser.setLastName(userUpdateRequest.getLastName());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> new ResourceNotFoundException("User not found"));

    }



    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }


}
