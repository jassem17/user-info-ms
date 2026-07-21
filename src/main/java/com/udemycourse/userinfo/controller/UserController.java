package com.udemycourse.userinfo.controller;

import com.udemycourse.userinfo.DTO.UserDTO;
import com.udemycourse.userinfo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        UserDTO userCreated = userService.saveUser(userDTO);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable int id){
        return userService.fetchUserById(id);
    }
}
