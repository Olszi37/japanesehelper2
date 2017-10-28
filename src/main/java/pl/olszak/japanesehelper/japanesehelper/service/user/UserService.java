package pl.olszak.japanesehelper.japanesehelper.service.user;

import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.UserDTO;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

public interface UserService extends ServiceInterface<UserEntity, UserDTO, Long>{

    UserDTO save(UserDTO dto);
}
