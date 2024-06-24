package com.mruruc.service;


import com.mruruc.exception.PasswordMismatchException;
import com.mruruc.exception.UserNotFoundException;
import com.mruruc.model.Address;
import com.mruruc.model.User;
import com.mruruc.repositoryImpl.UserRepositoryImpl;
import com.mruruc.validation.DtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final DtoValidator<User> dtoValidator;
    private final UserRepositoryImpl userRepository;
    @Autowired
    public UserService(DtoValidator<User> dtoValidator, UserRepositoryImpl userRepository) {
        this.dtoValidator = dtoValidator;
        this.userRepository = userRepository;
    }

    public User saveUser(User userDto) {
        dtoValidator.validate(userDto);
        // save user
        User savedUser= userRepository.save(userDto);
        // prepare dto
        User dto=new User();
        dto.setUserId(savedUser.getUserId());
        dto.setUsername(savedUser.getUsername());
        // return dto
        return dto;
    }

    public void saveAddress(Address address){
        userRepository.addAddress(address);
    }

    public User loginCredential(User userDto) {
        Optional<User> userByUsername = userRepository.findUserByUsername(userDto.getUsername());
        if (userByUsername.isPresent()) {
            User user = userByUsername.get();
            if (user.getPassword().equals(userDto.getPassword())) {
                userDto.setUserId(user.getUserId());
                return userDto;
            }
            throw new PasswordMismatchException("Password mismatch");
        }
        throw new UserNotFoundException("with username " + userDto.getUsername() + " not found");
    }
}
