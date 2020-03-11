package com.automappinghomework.services;

import com.automappinghomework.domain.models.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
}
