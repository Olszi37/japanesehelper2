package pl.olszak.japanesehelper.japanesehelper.converter.user;

import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.converter.Converter;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.UserDTO;

@Component
public class UserConverter implements Converter<UserEntity, UserDTO> {

    @Override
    public UserEntity convertToEntity(UserDTO dto) {
        return null;
    }

    @Override
    public UserDTO convertToDTO(UserEntity entity) {
        return null;
    }
}
