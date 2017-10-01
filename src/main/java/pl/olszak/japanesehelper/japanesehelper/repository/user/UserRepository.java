package pl.olszak.japanesehelper.japanesehelper.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

    Optional<UserEntity> findOneByLogin(String login);
}
