package pl.olszak.japanesehelper.japanesehelper.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
