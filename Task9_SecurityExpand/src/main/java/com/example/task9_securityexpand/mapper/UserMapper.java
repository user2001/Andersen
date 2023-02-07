package com.example.task9_securityexpand.mapper;

import com.example.task9_securityexpand.dto.UserRequest;
import com.example.task9_securityexpand.dto.UserResponse;
import com.example.task9_securityexpand.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toDtoResponse(User user);

    User toEntity(UserRequest userRequest);
    User toEntity(UserResponse userResponse);
}
