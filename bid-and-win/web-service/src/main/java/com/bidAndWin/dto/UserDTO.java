package com.bidAndWin.dto;

import com.bidAndWin.model.user.User;
import com.bidAndWin.validation.customValidation.Password;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long userId;
    @NotNull(message = "Username can not be empty!")
    @NotEmpty(message = "Username can not be empty!")
    @Email(message = "must be valid email. ex,****@****.com")
    private String userName;
    @Password(message = "password must be at least 8 character!")
   // @Pattern(regexp = "")
    private String password;
    private boolean isAccountLocked;
    private Long failedLoginAttempts;
    private LocalDate lastModified;
   // @NotEmpty
    private Long clientId;

    public UserDTO() {}

    public static UserDTO toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDto = new UserDTO();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUsername());
        userDto.setPassword(user.getPasswordHash());
        userDto.setAccountLocked(user.getIsAccountLocked());
        userDto.setFailedLoginAttempts(user.getFailedLoginAttempts());
        userDto.setLastModified(user.getLastModified());
        userDto.setClientId(user.getClient().getClientId());
        return userDto;
    }

}
