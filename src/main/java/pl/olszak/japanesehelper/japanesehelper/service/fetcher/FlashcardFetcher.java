package pl.olszak.japanesehelper.japanesehelper.service.fetcher;

import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;

import java.util.List;

public interface FlashcardFetcher<FLASHCARD extends RecordEntity> {

    List<FLASHCARD> getFlashcards(JLPTLevel level, UserEntity userEntity);

    boolean supports(FlashcardType type);
}
