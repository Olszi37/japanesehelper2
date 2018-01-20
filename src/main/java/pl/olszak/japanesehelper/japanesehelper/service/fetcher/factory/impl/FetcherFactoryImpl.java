package pl.olszak.japanesehelper.japanesehelper.service.fetcher.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.factory.FetcherFactory;

import java.util.List;
import java.util.Optional;

@Component
public class FetcherFactoryImpl implements FetcherFactory {

    @Autowired
    List<FlashcardFetcher> fetchers;

    @Override
    public FlashcardFetcher getFetcher(FlashcardType type) {
        Optional<FlashcardFetcher> maybeFetcher = fetchers.stream()
                .filter(fetcher -> fetcher.supports(type)).findFirst();
        if(maybeFetcher.isPresent()){
            return maybeFetcher.get();
        }
        throw new IllegalStateException("No fetcher found for type: " + type);
    }
}
