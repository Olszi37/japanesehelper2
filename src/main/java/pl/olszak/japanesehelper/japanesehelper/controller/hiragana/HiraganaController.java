package pl.olszak.japanesehelper.japanesehelper.controller.hiragana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.olszak.japanesehelper.japanesehelper.dto.hiraKata.HiraKataDTO;
import pl.olszak.japanesehelper.japanesehelper.service.hiragana.HiraganaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jhelper")
public class HiraganaController {

    private HiraganaService hiraganaService;

    @Autowired
    public HiraganaController(HiraganaService hiraganaService) {
        this.hiraganaService = hiraganaService;
    }

    @GetMapping("/hiragana/all")
    public ResponseEntity<List<HiraKataDTO>> getAll(){
        List<HiraKataDTO> kanas = hiraganaService.findAll();
        return ResponseEntity.ok(kanas);
    }

    @GetMapping("/hiragana/{id}")
    public ResponseEntity<HiraKataDTO> getOne(@PathVariable Long id){
        Optional<HiraKataDTO> dto = hiraganaService.findById(id);
        if(dto.isPresent()){
            return ResponseEntity.ok(dto.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
