package com.product.service;

import com.product.dto.UserApiDto;

import java.util.List;

public interface IUserService {

    List<UserApiDto> getAllUsers();
    UserApiDto getUserById(Long id);
    void deleteUserById(Long id);
    UserApiDto saveUser(UserApiDto userApiDto);
    UserApiDto updateUser(Long id, UserApiDto userApiDto);
}
