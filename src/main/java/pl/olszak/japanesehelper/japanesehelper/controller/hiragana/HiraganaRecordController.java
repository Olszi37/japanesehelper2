package pl.olszak.japanesehelper.japanesehelper.controller.hiragana;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardRequestDTO;

@RestController
@RequestMapping("/jhelper")
public class HiraganaRecordController {

    @GetMapping("/hiragana/flashcard")
    public ResponseEntity getFashcard(@RequestBody FlashcardRequestDTO dto){
        return null;
    }

    @PutMapping("/hiragana/record")
    public ResponseEntity saveRecord(){
        return null;
    }
}
