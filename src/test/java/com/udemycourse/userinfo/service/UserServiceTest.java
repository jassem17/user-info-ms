package com.udemycourse.userinfo.service;

import com.udemycourse.userinfo.DTO.UserDTO;
import com.udemycourse.userinfo.entity.User;
import com.udemycourse.userinfo.mapper.UserMapper;
import com.udemycourse.userinfo.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepo userRepo;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser(){
        UserDTO userDTO = new UserDTO();
        User user = UserMapper.INSTANCE.mapUserDTOToUser(userDTO);

        when(userRepo.save(user)).thenReturn(user);

        UserDTO savedUser = userService.saveUser(userDTO);

        assertEquals(savedUser,userDTO);

        verify(userRepo,times(1)).save(user);
    }

    @Test
    public void testFetchUserById_IDExist(){
        int id = 0;
        User user = new User();

        when(userRepo.findById(id)).thenReturn(Optional.of(user));

        ResponseEntity<UserDTO> response = userService.fetchUserById(id);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(UserMapper.INSTANCE.mapUserToUserDTO(user),response.getBody());
    }

    @Test
    public void testFetchUserById_IDNotExist(){
        int id = 0;

        when(userRepo.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = userService.fetchUserById(id);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody());
    }
}
