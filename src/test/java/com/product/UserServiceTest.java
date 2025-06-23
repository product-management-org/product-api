package com.product;

import com.product.dto.UserApiDto;
import com.product.entity.UserEntity;
import com.product.repository.IUserRepository;
import com.product.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserEntity user1;
    private UserEntity user2;

    @BeforeEach
    public void setup(){
        user1 = new UserEntity();
        user1.setId(1L);
        user1.setUsername("john");
        user1.setEmail("john@gmail.com");
        user1.setCreatedAt(Instant.now());
        user1.setUpdatedAt(Instant.now());

        user2 = new UserEntity();
        user2.setId(2L);
        user2.setUsername("imad");
        user2.setEmail("imad@gmail.com");
        user2.setCreatedAt(Instant.now());
        user2.setUpdatedAt(Instant.now());
    }

    @Test
    void test_getAllUsers(){
        List<UserEntity> listUsers = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(listUsers);
        List<UserApiDto> result = userService.getAllUsers();
        assertThat(result.getFirst().getId()).isEqualTo(1L);
        assertThat(result.getFirst().getUsername()).isEqualTo("john");
        assertThat(result.get(1).getUsername()).isEqualTo("imad");
        assertThat(result).hasSize(2);
    }
}
