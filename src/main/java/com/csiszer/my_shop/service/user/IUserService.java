package com.csiszer.my_shop.service.user;

import com.csiszer.my_shop.model.User;
import com.csiszer.my_shop.request.CreateUserRequest;
import com.csiszer.my_shop.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest createuserRequest);
    User updateUser(UserUpdateRequest userUpdateRequest, Long userId);
    void deleteUser(Long userId);

}
