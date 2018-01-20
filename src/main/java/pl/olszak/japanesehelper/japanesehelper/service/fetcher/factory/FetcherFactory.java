package pl.olszak.japanesehelper.japanesehelper.service.fetcher.factory;

import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;

public interface FetcherFactory {

    FlashcardFetcher getFetcher(FlashcardType type);
}
