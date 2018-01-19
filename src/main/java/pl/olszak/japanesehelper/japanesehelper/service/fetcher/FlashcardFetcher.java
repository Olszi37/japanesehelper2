package pl.olszak.japanesehelper.japanesehelper.service.fetcher;

import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;

import java.util.List;

public interface FlashcardFetcher<FLASHCARD extends RecordEntity> {

    List<FLASHCARD> getFlashcards();
}
