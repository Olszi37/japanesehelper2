package pl.olszak.japanesehelper.japanesehelper.controller.hiragana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.olszak.japanesehelper.japanesehelper.dto.kana.KanaDTO;
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
    public ResponseEntity<List<KanaDTO>> getAll(){
        List<KanaDTO> kanas = hiraganaService.findAll();
        return ResponseEntity.ok(kanas);
    }

    @GetMapping("/hiragana/{id}")
    public ResponseEntity<KanaDTO> getOne(@PathVariable Long id){
        Optional<KanaDTO> dto = hiraganaService.findOne(id);
        if(dto.isPresent()){
            return ResponseEntity.ok(dto.get());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
