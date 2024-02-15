package com.startline.startline.model.user.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Eugene Petrov
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRegistrationResponse {
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }


}
