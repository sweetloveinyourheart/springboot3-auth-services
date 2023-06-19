package onlinebookstore.bookstoreservice.auth.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SignInResponse {
    String accessToken;
}
