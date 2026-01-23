package com.springexercise.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserDto {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @NotNull(message = "name is required")
    @NotBlank(message = "name can not be empty")
    @Size(min = 4 , max = 15 , message = "name at least 4 to 15 digits")
    private String name;


    private int age;

    @Email(message = "email must end with @gmail.com")
    @NotNull(message = "email is required")
    @NotBlank(message = "email can not be empty")
    @Size(min = 10 , max = 25 , message = "email at least 10 to 25 digits")
    private String email;

    @NotNull(message = "password is required")
    @NotBlank(message = "password can not be empty")
    @Size(min = 5 , max = 10 , message = "password at least 5 to 10 digits")
    private String password;


    private String role;
}
