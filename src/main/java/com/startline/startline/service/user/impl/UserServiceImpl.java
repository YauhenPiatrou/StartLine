package com.startline.startline.service.user.impl;

import com.startline.startline.model.user.User;
import com.startline.startline.model.user.registration.UserRegistrationRequest;
import com.startline.startline.model.user.registration.UserRegistrationResponse;
import com.startline.startline.repository.user.UserRepository;
import com.startline.startline.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 * @author Eugene Petrov
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
        // Логика регистрации нового пользователя



            User newUser = new User(request.getUsername(), request.getPassword(), request.getEmail());
            userRepository.save(newUser);

            // Возврат ответа о успешной регистрации
            return new UserRegistrationResponse(true, "User registered successfully");
        }
    }

