package com.startline.startline.model.user.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eugene Petrov
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRegistrationRequest {
    @NotBlank
    @NotNull
    private String username;

    @Size(min = 6,message = "Password must contain min 6 symbols")
    private String password;

    @Email
    private String email;
}
