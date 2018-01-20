package pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.repository.record.KatakanaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.Fetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;

import java.util.List;

@Component
public class KatakanaFetcher extends Fetcher<KatakanaRecordEntity>
        implements FlashcardFetcher<KatakanaRecordEntity> {

    private final KatakanaRecordRepository katakanaRecordRepository;

    @Autowired
    public KatakanaFetcher(KatakanaRecordRepository katakanaRecordRepository) {
        this.katakanaRecordRepository = katakanaRecordRepository;
    }

    @Override
    public List<KatakanaRecordEntity> getGroup1(JLPTLevel level) {
        return katakanaRecordRepository.getRecordsBetweenGroup1();
    }

    @Override
    public List<KatakanaRecordEntity> getGroup2(JLPTLevel level) {
        return katakanaRecordRepository.getRecordsBetweenGroup2();
    }

    @Override
    public List<KatakanaRecordEntity> getGroup3(JLPTLevel level) {
        return katakanaRecordRepository.getRecordsBetweenGroup3();
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.KATAKANA.equals(type);
    }
}
