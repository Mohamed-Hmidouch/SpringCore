package com.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserDto {

    public CreateUserDto(){
        
    }

    @NotBlank(message = "Username is not blank")
    @Size(min = 2 , max = 20, message = "Username is between 2 and 20 characters")
    private String username;

    @NotBlank(message = "Email is not blank")
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 4 , message = "Password must be at least 4 characters")
    @NotBlank(message = "password need to be not blank")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
