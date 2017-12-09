package pl.olszak.japanesehelper.japanesehelper.service.record.factory;

import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;

public interface RecordFactory {

    RecordService getService(FlashcardType type) throws IllegalStateException;
}
