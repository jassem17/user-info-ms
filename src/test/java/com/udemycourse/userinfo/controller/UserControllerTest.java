package com.udemycourse.userinfo.controller;

import com.udemycourse.userinfo.DTO.UserDTO;
import com.udemycourse.userinfo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUser(){
        UserDTO mockUser = new UserDTO();
        mockUser.setId(123);
        mockUser.setUserName("John Doe");
        mockUser.setUserPassword("12345");
        mockUser.setAddress("user address");
        mockUser.setCity("user city");

        when(userService.saveUser(mockUser)).thenReturn(mockUser);

        ResponseEntity<UserDTO> response = userController.addUser(mockUser);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockUser, response.getBody());

        verify(userService,times(1)).saveUser(mockUser);
    }

    @Test
    public void testFetchUserById(){
        int mockId = 0;
        UserDTO mockUser = new UserDTO();
        mockUser.setId(0);
        mockUser.setUserName("John Doe");
        mockUser.setUserPassword("12345");
        mockUser.setAddress("user address");
        mockUser.setCity("user city");

        when(userService.fetchUserById(mockId)).thenReturn(new ResponseEntity<>(mockUser,HttpStatus.OK));

        ResponseEntity<UserDTO> response = userController.fetchUserById(mockId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUser, response.getBody());

        verify(userService, times(1)).fetchUserById(mockId);

    }
}
