package com.startline.startline.controller;

import com.startline.startline.controller.user.UserRegistrationController;
import com.startline.startline.model.user.registration.UserRegistrationRequest;
import com.startline.startline.model.user.registration.UserRegistrationResponse;
import com.startline.startline.service.user.UserService;
import com.startline.startline.service.user.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @author Eugene Petrov
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserRegistrationControllerTest.class)
public class UserRegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private UserRegistrationController userRegistrationController;

    @Mock
    private UserServiceImpl userService;


    @Test
    public void testRegisterUser_SuccessfulRegistration() throws Exception {
        // Подготовка данных для успешной регистрации
        UserRegistrationRequest request = new UserRegistrationRequest("username", "password", "example@email.com");
        UserRegistrationResponse response = new UserRegistrationResponse(true, "User registered successfully");
        ObjectMapper objectMapper = new ObjectMapper();
        // Настройка мока для успешной регистрации
        when(userService.registerUser(any(UserRegistrationRequest.class))).thenReturn(response);

        // Инициализация MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(userRegistrationController).build();

        // Выполнение запроса к контроллеру и проверка ожидаемого ответа
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRegisterUser_Failure_InvalidRequest() {
        UserRegistrationRequest request = new UserRegistrationRequest(null, "password", "example@email.com");
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(request, "request");
        bindingResult.addError(new ObjectError("request", "Username cannot be blank"));

        ResponseEntity<UserRegistrationResponse> actualResponseEntity = userRegistrationController.registerUser(request, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, actualResponseEntity.getStatusCode());
        UserRegistrationResponse actualResponse = actualResponseEntity.getBody();
        assertEquals(false, actualResponse.isSuccess());
        assertEquals("Not valid user data", actualResponse.getMessage());
    }
}