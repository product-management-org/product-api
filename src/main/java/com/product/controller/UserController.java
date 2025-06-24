package com.product.controller;

import com.product.dto.UserApiDto;
import com.product.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserApiDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<UserApiDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("delete/user/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user")
    public ResponseEntity<UserApiDto> saveUser(@RequestBody UserApiDto userApiDto){
        return ResponseEntity.ok(userService.saveUser(userApiDto));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserApiDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserApiDto userApiDto){
        return ResponseEntity.ok(userService.updateUser(id, userApiDto));

    }


}
