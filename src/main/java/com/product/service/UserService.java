package com.product.service;

import com.product.dto.UserApiDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Override
    public List<UserApiDto> getAllUsers() {
        return List.of();
    }
}
