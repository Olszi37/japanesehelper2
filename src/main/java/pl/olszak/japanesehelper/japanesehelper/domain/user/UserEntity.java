package pl.olszak.japanesehelper.japanesehelper.domain.user;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements EntityInterface {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_gen")
    private Long id;

    @NotNull
    @Column(name = "login")
    @Size(max = 100)
    private String login;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "email")
    private String email;
}
