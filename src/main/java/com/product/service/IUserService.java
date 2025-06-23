package com.product.service;

import com.product.dto.UserApiDto;

import java.util.List;

public interface IUserService {

    List<UserApiDto> getAllUsers();
}
