package com.automappinghomework.services;

import com.automappinghomework.domain.models.UserLoginDto;
import com.automappinghomework.domain.models.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
    void loginUser(UserLoginDto userLoginDto);
    void logoutUser();
}
