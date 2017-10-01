package pl.olszak.japanesehelper.japanesehelper.domain.user;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.Authorities;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements EntityInterface {

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(
            name = "users_id_gen",
            allocationSize = 1,
            sequenceName = "users_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private Authorities authority;
}
