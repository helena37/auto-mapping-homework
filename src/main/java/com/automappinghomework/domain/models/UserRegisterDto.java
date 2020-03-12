package com.automappinghomework.domain.models;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterDto {
    private String email;
    private String password;
    private String fullName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    @Pattern(regexp = ".+@.+\\..+", message = "!!!Email is not valid!!!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 6, max = 6, message = "Password length must be 6 symbols!")
    @Pattern(regexp = "[A-Z]+[a-z]+[0-9]+", message = "Password must be 6 symbols!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
