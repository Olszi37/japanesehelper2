package pl.olszak.japanesehelper.japanesehelper.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.olszak.japanesehelper.japanesehelper.domain.Authorities;

import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends AbstractDTO{

    private Long id;

    @NotBlank
    @Size(max = 100)
    private String login;

    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @NotBlank
    @Email
    private String email;
    
    private Authorities authorities;
}
