package pl.olszak.japanesehelper.japanesehelper.controller.hiragana;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardRequestDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.KanaFlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.record.factory.RecordFactoryImpl;
import pl.olszak.japanesehelper.japanesehelper.service.record.impl.HiraganaRecordService;

import java.util.List;

@RestController
@RequestMapping("/jhelper")
public class HiraganaRecordController {

    @GetMapping("/hiragana/flashcards")
    public ResponseEntity getFashcard(@RequestBody FlashcardRequestDTO dto) {
        RecordService recordService = new RecordFactoryImpl().getService(FlashcardType.HIRAGANA);
        List<KanaFlashcardDTO> kanaFlashcards = ((HiraganaRecordService) recordService).getFlashcards(dto.getLevel(), dto.getFlashcardQuantity());
        return ResponseEntity.ok(kanaFlashcards);
    }

    @PutMapping("/hiragana/records")
    public void saveRecord(@RequestBody List<UserRecordDTO> recordDTOS) {
        RecordService recordService = new RecordFactoryImpl().getService(FlashcardType.HIRAGANA);
        recordService.save(recordDTOS);
    }
}