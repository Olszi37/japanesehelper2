package pl.olszak.japanesehelper.japanesehelper.service.record;

import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.StatsDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;

import java.util.List;

public interface RecordService<FLASHCARD extends FlashcardDTO> {

    void save(List<UserRecordDTO> recordDTOs);

    List<FLASHCARD> getFlashcards(JLPTLevel level, int flashcardCount);

    StatsDTO getStats(JLPTLevel level);

    default boolean supports(FlashcardType type){
        return false;
    }
}
