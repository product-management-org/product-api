package com.product.mapper;

import com.product.dto.UserApiDto;
import com.product.entity.UserEntity;

import java.time.Instant;
import java.time.ZoneOffset;

public class UserMapper {

    public static UserEntity toNewEntity(UserApiDto dto){
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return user;
    }

    public static UserEntity toUpdateEntity(UserApiDto dto, UserEntity user){
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUpdatedAt(Instant.now());
        return user;
    }

    public static UserApiDto toDto(UserEntity entity){
        UserApiDto dto = new UserApiDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setCreatedAt(entity.getCreatedAt().atOffset(ZoneOffset.UTC));
        dto.setUpdatedAt(entity.getUpdatedAt().atOffset(ZoneOffset.UTC));
        return dto;
    }
}
