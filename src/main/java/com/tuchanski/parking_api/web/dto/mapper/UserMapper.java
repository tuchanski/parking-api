package com.tuchanski.parking_api.web.dto.mapper;

import com.tuchanski.parking_api.entities.User;
import com.tuchanski.parking_api.web.dto.UserCreateDto;
import com.tuchanski.parking_api.web.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UserMapper {

    public static User toUser(UserCreateDto userCreateDto) {
        return new ModelMapper().map(userCreateDto, User.class);
    }

    public static UserResponseDto toDTO(User user) {

        String role = user.getRole().name().substring("ROLE_".length());
        PropertyMap<User, UserResponseDto> props = new PropertyMap<User, UserResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(user, UserResponseDto.class);

    }

}
