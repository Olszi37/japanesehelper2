package pl.olszak.japanesehelper.japanesehelper.service.record;

import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;

public interface RecordService {

    default boolean supports(FlashcardType type){
        return false;
    }
}
