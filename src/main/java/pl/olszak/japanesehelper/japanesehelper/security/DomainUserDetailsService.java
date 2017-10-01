package pl.olszak.japanesehelper.japanesehelper.security;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;

    @Autowired
    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        log.debug("Authenticating {}", login);
        Optional<UserEntity> userFromDatabase = userRepository.findOneByLogin(login);
        if(!userFromDatabase.isPresent()){
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        }
        List<GrantedAuthority> authorities = Lists.newArrayList(
                new SimpleGrantedAuthority(userFromDatabase.get().getAuthority().toString()));
        return new User(userFromDatabase.get().getLogin(), userFromDatabase.get().getPassword(), authorities);
    }
}
