package onlinebookstore.bookstoreservice.auth.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequest {
    @NotBlank
    String email;

    @NotBlank
    String password;
}
