package pl.olszak.japanesehelper.japanesehelper.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.olszak.japanesehelper.japanesehelper.dto.UserDTO;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/jhelper")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO dto){
        UserDTO user;
        try{
            user = service.save(dto);
        } catch (Exception e) {
            log.error("Can't create user: {}", e);
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto){
        return save(dto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getOneUser(@PathVariable Long id){
        Optional<UserDTO> user;
        user = service.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }
}
