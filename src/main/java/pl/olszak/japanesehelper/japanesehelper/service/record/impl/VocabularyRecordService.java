package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;

@Service
@Transactional
public class VocabularyRecordService implements RecordService {
    @Override
    public void save(UserRecordDTO recordDTO) {

    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.VOCABULARY.equals(type);
    }
}
