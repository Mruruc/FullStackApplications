package com.bidAndWin.service;

import com.bidAndWin.exceptions.EntityNotFoundException;
import com.bidAndWin.exceptions.IncorrectPasswordException;
import com.bidAndWin.exceptions.UserNotFoundException;
import com.bidAndWin.dto.UserDTO;
import com.bidAndWin.model.client.Client;
import com.bidAndWin.model.user.User;
import com.bidAndWin.repository.UserRepository;
import com.bidAndWin.validation.DtoValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ClientService clientService;
    private final DtoValidator<UserDTO> dtoValidator;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, ClientService clientService,
                       DtoValidator<UserDTO> dtoValidator) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.clientService = clientService;
        this.dtoValidator = dtoValidator;
    }

    public UserDTO saveUser(Long clientId,UserDTO userDto){
        // validate
        dtoValidator.validate(userDto);

        // check if client exists
        Client clientById = clientService.findClientById(clientId);
        // crate new user instance
        User newUser = new User();
        newUser.setUsername(userDto.getUserName());
        newUser.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
        newUser.setIsAccountLocked(false);
        newUser.setFailedLoginAttempts(0L);
        newUser.setLastModified(LocalDate.now());
        newUser.setClient(clientById);

        User save = userRepository.save(newUser);
        userDto.setClientId(clientId);
        userDto.setUserId(save.getUserId());
        return userDto;
    }


    public UserDTO getUserById(Long userId) {
        Optional<User> userByClientId = userRepository.findById(userId);
        if (userByClientId.isPresent()) {
            User user = userByClientId.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setClientId(user.getClient().getClientId());
            userDTO.setUserName(user.getUsername());
           // userDTO.setPassword(null);
            return userDTO;
        } else {
            throw new EntityNotFoundException("User not found!");
        }
    }


    public UserDTO login(UserDTO userDTO){
        Optional<User> savedUser = userRepository.findByUsername(userDTO.getUserName());
        if(savedUser.isPresent()){
            User user = savedUser.get();
            if(checkPassword(userDTO.getPassword(),user.getPasswordHash())){

                user.setFailedLoginAttempts(0L);
                updateFailedLoginAttempts(user);

                userDTO.setUserId(user.getUserId());
                userDTO.setClientId(user.getClient().getClientId());
                userDTO.setUserName(user.getUsername());

                return userDTO;
            }

            user.setFailedLoginAttempts(user.getFailedLoginAttempts() +1);
            updateFailedLoginAttempts(user);
            throw new IncorrectPasswordException("Incorrect password!");

        }
        throw new EntityNotFoundException("User not found within given credentials!");
    }


    private void updateFailedLoginAttempts(User user){
       userRepository.updateFailedLoginAttemptsByUserId(user.getUserId(),user.getFailedLoginAttempts());
    }
    private boolean checkPassword(String rawPassword,String hashedPassword){
        return passwordEncoder.matches(rawPassword,hashedPassword);
    }
}
