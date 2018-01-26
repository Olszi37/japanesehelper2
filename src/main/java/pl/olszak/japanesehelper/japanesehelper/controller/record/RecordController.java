package pl.olszak.japanesehelper.japanesehelper.controller.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.dto.record.*;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.record.factory.RecordFactory;
import pl.olszak.japanesehelper.japanesehelper.service.record.impl.HiraganaRecordService;
import pl.olszak.japanesehelper.japanesehelper.service.record.impl.KanjiRecordService;
import pl.olszak.japanesehelper.japanesehelper.service.record.impl.KatakanaRecordService;
import pl.olszak.japanesehelper.japanesehelper.service.record.impl.VocabularyRecordService;

import java.util.List;

@RestController
@RequestMapping("/jhelper/record")
public class RecordController {

    private final RecordFactory recordFactory;

    @Autowired
    public RecordController(RecordFactory recordFactory) {
        this.recordFactory = recordFactory;
    }

    @PostMapping("/hiragana")
    public ResponseEntity<Void> saveHiragana(@RequestBody List<UserRecordDTO> dtos) throws Exception {
        RecordService service = getRecordService(FlashcardType.HIRAGANA);
        service.save(dtos);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/hiragana")
    public ResponseEntity<List<KanaFlashcardDTO>> getHiraganaFlashcard() {
        RecordService service = getRecordService(FlashcardType.HIRAGANA);
        List<KanaFlashcardDTO> flashcards = ((HiraganaRecordService)service).getFlashcards(null, 0);
        return ResponseEntity.ok(flashcards);
    }

    @PostMapping("/katakana")
    public ResponseEntity<Void> saveKatakana(@RequestBody List<UserRecordDTO> dtos) throws Exception {
        RecordService service = getRecordService(FlashcardType.KATAKANA);
        service.save(dtos);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/katakana")
    public ResponseEntity<List<KanaFlashcardDTO>> getKatakanaFlashcard() {
        RecordService service = getRecordService(FlashcardType.KATAKANA);
        List<KanaFlashcardDTO> flashcards = ((KatakanaRecordService)service).getFlashcards(null, 0);
        return ResponseEntity.ok(flashcards);
    }

    @PostMapping("/kanji")
    public ResponseEntity<Void> saveKanji(@RequestBody List<UserRecordDTO> dtos) throws Exception {
        RecordService service = getRecordService(FlashcardType.KANJI);
        service.save(dtos);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/kanji/{level}")
    public ResponseEntity<List<KanjiFlashcardDTO>> getKanjiFlashcard(@PathVariable JLPTLevel level) {
        RecordService service = getRecordService(FlashcardType.KANJI);
        List<KanjiFlashcardDTO> flashcards = ((KanjiRecordService)service).getFlashcards(level, 0);
        return ResponseEntity.ok(flashcards);
    }

    @PostMapping("/vocabulary")
    public ResponseEntity<Void> saveVocabulary(@RequestBody List<UserRecordDTO> dtos) throws Exception {
        RecordService service = getRecordService(FlashcardType.VOCABULARY);
        service.save(dtos);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/vocabulary/{level}")
    public ResponseEntity<List<VocabularyFlashcardDTO>> getVocabularyFlashcard(@PathVariable JLPTLevel level) {
        RecordService service = getRecordService(FlashcardType.VOCABULARY);
        List<VocabularyFlashcardDTO> flashcards = ((VocabularyRecordService)service).getFlashcards(level, 0);
        return ResponseEntity.ok(flashcards);
    }

    private RecordService getRecordService(FlashcardType type) {
        return recordFactory.getService(type);
    }
}
