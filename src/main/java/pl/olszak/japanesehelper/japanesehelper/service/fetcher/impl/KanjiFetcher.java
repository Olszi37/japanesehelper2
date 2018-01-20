package pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.repository.record.KanjiRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.Fetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;

import java.util.List;

@Component
public class KanjiFetcher extends Fetcher<KanjiRecordEntity> implements FlashcardFetcher<KanjiRecordEntity> {

    private final KanjiRecordRepository kanjiRecordRepository;

    @Autowired
    public KanjiFetcher(KanjiRecordRepository kanjiRecordRepository) {
        this.kanjiRecordRepository = kanjiRecordRepository;
    }

    @Override
    public List<KanjiRecordEntity> getGroup1(JLPTLevel level) {
        return kanjiRecordRepository.getRecordsBetweenGroup1(level);
    }

    @Override
    public List<KanjiRecordEntity> getGroup2(JLPTLevel level) {
        return kanjiRecordRepository.getRecordsBetweenGroup2(level);
    }

    @Override
    public List<KanjiRecordEntity> getGroup3(JLPTLevel level) {
        return kanjiRecordRepository.getRecordsBetweenGroup3(level);
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.KANJI.equals(type);
    }
}
