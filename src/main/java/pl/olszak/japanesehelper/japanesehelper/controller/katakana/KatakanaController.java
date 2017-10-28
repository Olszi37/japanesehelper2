package pl.olszak.japanesehelper.japanesehelper.controller.katakana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.olszak.japanesehelper.japanesehelper.dto.hiraKata.HiraKataDTO;
import pl.olszak.japanesehelper.japanesehelper.service.katakana.KatakanaServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jhelper")
public class KatakanaController {

    private KatakanaServiceImpl katakanaService;

    @Autowired
    public KatakanaController(KatakanaServiceImpl katakanaService) {
        this.katakanaService = katakanaService;
    }

    @GetMapping("/katakana/all")
    public ResponseEntity<List<HiraKataDTO>> getAll(){
        List<HiraKataDTO> kanas = katakanaService.findAll();
        return ResponseEntity.ok(kanas);
    }

    @GetMapping("/katakana/{id}")
    public ResponseEntity<HiraKataDTO> getOne(@PathVariable Long id){
        Optional<HiraKataDTO> dto = katakanaService.findOne(id);
        if(dto.isPresent()){
            return ResponseEntity.ok(dto.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
