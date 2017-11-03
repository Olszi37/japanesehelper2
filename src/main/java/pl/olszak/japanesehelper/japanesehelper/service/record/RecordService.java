package pl.olszak.japanesehelper.japanesehelper.service.record;

import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;

public interface RecordService {

    void save(UserRecordDTO recordDTO);

    default boolean supports(FlashcardType type){
        return false;
    }
}
