package pl.olszak.japanesehelper.japanesehelper.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.olszak.japanesehelper.japanesehelper.JapanesehelperApplication;
import pl.olszak.japanesehelper.japanesehelper.controller.user.UserJWTController;
import pl.olszak.japanesehelper.japanesehelper.dto.LoginDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.UserDTO;
import pl.olszak.japanesehelper.japanesehelper.security.jwt.TokenProvider;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;
import pl.olszak.japanesehelper.japanesehelper.util.TestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JapanesehelperApplication.class)
public class UserJWTTest {

    private static final String USER_LOGIN = "newLogin";

    private static final String USER_PASSWORD = "somePassword";
    private static final String USER_BAD_PASSWORD = "badPassword";

    private static final String USER_EMAIL = "some@email.com";

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restJwtMock;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        UserJWTController userJWTController = new UserJWTController(tokenProvider, authenticationManager);
        this.restJwtMock = MockMvcBuilders.standaloneSetup(userJWTController)
                .setMessageConverters(jacksonMessageConverter).build();
    }

    @Test
    public void authenticationSuccessTest() throws Exception{

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(USER_LOGIN);
        userDTO.setPassword(USER_PASSWORD);
        userDTO.setEmail(USER_EMAIL);

        userService.save(userDTO);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setLogin(USER_LOGIN);
        loginDTO.setPassword(USER_PASSWORD);

        restJwtMock.perform(post("/jhelper/authorize")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_token").isString())
                .andExpect(jsonPath("$.id_token").isNotEmpty());
    }

    public void authenticateFailureTest() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(USER_LOGIN);
        userDTO.setPassword(USER_PASSWORD);
        userDTO.setEmail(USER_EMAIL);

        userService.save(userDTO);

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setLogin(USER_LOGIN);
        loginDTO.setPassword(USER_BAD_PASSWORD);

        restJwtMock.perform(post("jhelper/authorize")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(loginDTO)))
                .andExpect(status().isBadRequest());
    }
}
