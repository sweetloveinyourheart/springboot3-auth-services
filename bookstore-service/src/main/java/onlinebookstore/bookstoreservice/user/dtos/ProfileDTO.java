package onlinebookstore.bookstoreservice.user.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
public class ProfileDTO {
    @NotBlank
    @Size(min = 3, message = "Username must have at least 3 character")
    String fullName;

    private String address;

    private String phone;

    private String birthday;

}
