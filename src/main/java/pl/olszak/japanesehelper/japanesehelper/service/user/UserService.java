package pl.olszak.japanesehelper.japanesehelper.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.olszak.japanesehelper.japanesehelper.converter.user.UserConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.UserDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.UserRepository;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements ServiceInterface<UserDTO> {

    private UserRepository userRepository;
    private UserConverter converter;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter converter) {
        this.userRepository = userRepository;
        this.converter = converter;
    }

    @Override
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(converter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        Optional<UserEntity> entity = Optional.ofNullable(userRepository.findOne(id));
        return entity.map(userEntity -> converter.convertToDTO(userEntity));
    }

    @Override
    public UserDTO save(UserDTO dto){
        UserEntity entity = converter.convertToEntity(dto);
        return converter.convertToDTO(userRepository.save(entity));
    }

    @Override
    public void delete(UserDTO dto){
        UserEntity entity = converter.convertToEntity(dto);
        userRepository.delete(entity);
    }

}
