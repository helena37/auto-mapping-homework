package com.automappinghomework.services;

import com.automappinghomework.domain.entities.Role;
import com.automappinghomework.domain.entities.User;
import com.automappinghomework.domain.models.dtos.UserDto;
import com.automappinghomework.domain.models.dtos.UserLoginDto;
import com.automappinghomework.domain.models.dtos.UserRegisterDto;
import com.automappinghomework.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = this.modelMapper.map(userRegisterDto, User.class);

        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = this.userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (user == null) {
            System.out.println("Incorrect username / password");
            return;
        } else {
            this.userDto = this.modelMapper.map(user, UserDto.class);
            System.out.println("Successfully logged in " + this.userDto.getFullName());
        }
    }

    @Override
    public void logoutUser() {
        if (this.userDto == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            String name = this.userDto.getFullName();
            this.userDto = null;
            System.out.println(String.format("User %s successfully logged out", name));
        }
    }

    @Override
    public boolean isLoggedUserAdmin() {
        return this.userDto.getRole().equals(Role.ADMIN);
    }
}
