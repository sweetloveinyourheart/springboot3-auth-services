package onlinebookstore.bookstoreservice.user.dtos;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class UserDTO {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    @Valid
    private ProfileDTO profile;
}
