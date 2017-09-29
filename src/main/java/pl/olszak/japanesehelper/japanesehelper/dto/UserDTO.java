package pl.olszak.japanesehelper.japanesehelper.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    private Long id;

    @NotBlank
    private String login;

    @NotNull
    @Size(min = 5)
    private String password;

    @Email
    private String email;
}
