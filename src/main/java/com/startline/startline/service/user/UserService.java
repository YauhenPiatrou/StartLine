package com.startline.startline.service.user;

import com.startline.startline.model.user.registration.UserRegistrationRequest;
import com.startline.startline.model.user.registration.UserRegistrationResponse;
import org.springframework.validation.BindingResult;

/**
 * @author Eugene Petrov
 */

public interface UserService {
    UserRegistrationResponse registerUser(UserRegistrationRequest request);
}
