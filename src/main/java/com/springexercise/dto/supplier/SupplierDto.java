package com.springexercise.dto.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class SupplierDto {


    @NotBlank(message = "name can not be empty!")
    @NotNull(message = "name is required")
    @Size(min = 4 , max = 40 , message = "supplier name must be between 5 to 40 characters")
    private String name;


    @Size(max = 100 , message = "address can not be more than 100 characters")
    private String address;

    @Size(max = 15 , message = "phone can not be more than 15 characters")
    private String phone;


    @Email(message = "email must be valid")
    @Size(max = 50 , message = "email can not be more than 50 characters")
    private String email;

    @Size(max = 50 , message = "rating can not be more than 50 characters")
    private String rating;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }

}