package com.product.service;

import com.product.dto.UserApiDto;
import com.product.entity.ProductEntity;
import com.product.entity.UserEntity;
import com.product.exception.ErrorCode;
import com.product.exception.ProductException;
import com.product.mapper.ProductMapper;
import com.product.mapper.UserMapper;
import com.product.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public List<UserApiDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserApiDto getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw new ProductException(ErrorCode.USER_NOT_FOUND, "user does not exist");
        }
        return UserMapper.toDto(user.get());
    }
}
