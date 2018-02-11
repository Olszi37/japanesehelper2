package pl.olszak.japanesehelper.japanesehelper.user;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.olszak.japanesehelper.japanesehelper.JapanesehelperApplication;
import pl.olszak.japanesehelper.japanesehelper.controller.user.UserController;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.UserDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.user.UserRepository;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;
import pl.olszak.japanesehelper.japanesehelper.util.TestUtil;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JapanesehelperApplication.class)
public class UserTests {

    private static final String USER_LOGIN = "newAnotherLogin";

    private static final String USER_PASSWORD = "somePassword";

    private static final String USER_EMAIL = "some2@email.com";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restUserMock;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        UserController userController = new UserController(userService);
        this.restUserMock = MockMvcBuilders.standaloneSetup(userController)
                .setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    public void createUserTest() throws Exception {

        int userCountBeforeSave = userRepository.findAll().size();

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(USER_LOGIN);
        userDTO.setPassword(USER_PASSWORD);
        userDTO.setEmail(USER_EMAIL);

        restUserMock.perform(post("/jhelper/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(userDTO)))
                .andExpect(status().isCreated());

        List<UserEntity> entities = userRepository.findAll();

        Assertions.assertThat(entities).hasSize(userCountBeforeSave + 1);

        UserEntity savedUser = entities.get(userCountBeforeSave);

        Assertions.assertThat(savedUser.getId()).isNotNull();
        Assertions.assertThat(savedUser.getLogin()).isEqualTo(USER_LOGIN);
        Assertions.assertThat(savedUser.getEmail()).isEqualTo(USER_EMAIL);
    }
}
