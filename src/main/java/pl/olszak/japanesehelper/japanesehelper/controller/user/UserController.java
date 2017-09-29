package pl.olszak.japanesehelper.japanesehelper.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.olszak.japanesehelper.japanesehelper.dto.UserDTO;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

@RestController
@RequestMapping("/jhelper")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/user")
    public ResponseEntity<UserDTO> save(UserDTO dto){
        UserDTO user;
        try{
            user = service.save(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDTO> update(UserDTO dto){
        return save(dto);
    }
}
