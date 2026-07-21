package com.udemycourse.userinfo.service;

import com.udemycourse.userinfo.DTO.UserDTO;
import com.udemycourse.userinfo.entity.User;
import com.udemycourse.userinfo.mapper.UserMapper;
import com.udemycourse.userinfo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public UserDTO saveUser(UserDTO userDTO) {
        User savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));
        return UserMapper.INSTANCE.mapUserToUserDTO(savedUser);
    }

    public ResponseEntity<UserDTO> fetchUserById(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDTO(user.get()),HttpStatus.OK);
    }
}
