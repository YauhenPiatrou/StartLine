package com.startline.startline.service.user.impl;

import com.startline.startline.model.user.User;
import com.startline.startline.model.user.registration.UserRegistrationRequest;
import com.startline.startline.model.user.registration.UserRegistrationResponse;
import com.startline.startline.repository.user.UserRepository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Eugene Petrov
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testRegisterUser_Success() {
        UserRegistrationRequest request = new UserRegistrationRequest("username", "password", "example@email.com");

        when(userRepository.save(any(User.class))).thenReturn(new User());

        UserRegistrationResponse actualResponse = userService.registerUser(request);

        assertTrue(actualResponse.isSuccess());
        assertEquals("User registered successfully", actualResponse.getMessage());

        verify(userRepository, times(1)).save(any(User.class));
    }

}

