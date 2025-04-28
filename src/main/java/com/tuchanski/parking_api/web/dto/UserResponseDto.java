package com.tuchanski.parking_api.web.dto;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class UserResponseDto {
    private Long id;
    private String username;
    private String role;
}
