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
import pl.olszak.japanesehelper.japanesehelper.service.AbstractService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<UserEntity, UserDTO, Long> implements UserService {

    private UserRepository userRepository;
    private UserConverter converter;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter converter,
                           PasswordEncoder passwordEncoder) {
        super(converter, userRepository);
        this.userRepository = userRepository;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(converter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO save(UserDTO dto){
        UserEntity entity = converter.convertToEntity(dto);
        entity.setAuthority(Authorities.ROLE_USER);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity = userRepository.save(entity);
        return converter.convertToDTO(entity);
    }

    //TODO refactor of saving user - hiding password

    //TODO changePassword

}