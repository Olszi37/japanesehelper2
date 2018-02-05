package pl.olszak.japanesehelper.japanesehelper.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.olszak.japanesehelper.japanesehelper.dto.UserDTO;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/jhelper")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO dto){
        UserDTO user;
        try{
            user = service.save(dto);
        } catch (Exception e) {
            log.error("Can't create user: {}", e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto){
        return save(dto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getOneUser(@PathVariable Long id){
        Optional<UserDTO> user;
        user = service.findOne(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/authenticate")
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    @GetMapping("/logged-user")
    public ResponseEntity<String> getLoggedUserLogin() {
        return ResponseEntity.ok(service.getLoggedUserLogin());
    }
}
