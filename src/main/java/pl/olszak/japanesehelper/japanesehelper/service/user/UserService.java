package pl.olszak.japanesehelper.japanesehelper.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.user.UserConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.Authorities;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.UserDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.user.UserRepository;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements ServiceInterface<UserDTO> {

    private UserRepository userRepository;
    private UserConverter converter;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter converter,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
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
        entity.setAuthority(Authorities.ROLE_USER);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        return converter.convertToDTO(userRepository.save(entity));
    }

    @Override
    public void delete(UserDTO dto){
        UserEntity entity = converter.convertToEntity(dto);
        userRepository.delete(entity);
    }

    //TODO changePassword

}
