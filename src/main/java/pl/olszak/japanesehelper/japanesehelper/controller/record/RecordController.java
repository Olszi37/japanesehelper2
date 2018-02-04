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

    @GetMapping("/hiragana/stats")
    public ResponseEntity<StatsDTO> getHiraganaStats() {
        RecordService service = getRecordService(FlashcardType.HIRAGANA);
        return ResponseEntity.ok(service.getStats(null));
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

    @GetMapping("/katakana/stats")
    public ResponseEntity<StatsDTO> getKatakanaStats() {
        RecordService service = getRecordService(FlashcardType.KATAKANA);
        return ResponseEntity.ok(service.getStats(null));
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

    @GetMapping("/kanji/{level}/stats")
    public ResponseEntity<StatsDTO> getKanjiStats(@PathVariable JLPTLevel level) {
        RecordService service = getRecordService(FlashcardType.KANJI);
        return ResponseEntity.ok(service.getStats(level));
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

    @GetMapping("/vocabulary/{level}/stats")
    public ResponseEntity<StatsDTO> getVocabularyStats(@PathVariable JLPTLevel level) {
        RecordService service = getRecordService(FlashcardType.VOCABULARY);
        return ResponseEntity.ok(service.getStats(level));
    }

    private RecordService getRecordService(FlashcardType type) {
        return recordFactory.getService(type);
    }
}
