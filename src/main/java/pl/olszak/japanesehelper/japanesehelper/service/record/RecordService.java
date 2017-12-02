package pl.olszak.japanesehelper.japanesehelper.service.record;

import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;

import java.util.List;

public interface RecordService {

    void save(UserRecordDTO recordDTO);

    List<Object> getFlashcards(JLPTLevel level, int flashcardCount);

    Object getFlashcard(JLPTLevel level);

    default boolean supports(FlashcardType type){
        return false;
    }
}
