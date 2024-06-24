package com.mruruc.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    private Long userId;
    @Email(message = "provide email with format ****@*****.com")
    @NotNull(message = "username can not be empty")
    @NotEmpty(message = "username can not be empty")
    private String username;
    @NotNull(message = "password can not be empty")
    @NotEmpty(message = "password can not be empty")
    private String password;
    @NotNull(message = "first name can not be empty")
    @NotEmpty(message = "first name can not be empty")
    private String firstName;
    @NotNull(message = "last name can not be empty")
    @NotEmpty(message = "last name can not be empty")
    private String lastName;
    @NotNull(message = "phone number can not be empty")
    @NotEmpty(message = "phone number can not be empty")
    private String phoneNumber;
    private String Address;
}
