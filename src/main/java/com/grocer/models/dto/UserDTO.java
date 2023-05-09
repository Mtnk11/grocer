package com.grocer.models.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.grocer.models.User;
import com.grocer.utils.CustomDateDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@Builder

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDTO {
    private Integer id;
    private String name;

    private String email;
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz")
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDateTime timestamp;
    private String password;

    public static User map(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .timestamp(userDTO.getTimestamp())
                .build();
    }
    public static UserDTO map(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .timestamp(user.getTimestamp())
                .build();
    }
}