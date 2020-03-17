package com.automappinghomework.services;

import com.automappinghomework.domain.models.dtos.UserLoginDto;
import com.automappinghomework.domain.models.dtos.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
    void loginUser(UserLoginDto userLoginDto);
    void logoutUser();
    boolean isLoggedUserAdmin();
}
