package pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.repository.record.HiraganaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.Fetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;

import java.util.List;

@Component
public class HiraganaFetcher extends Fetcher<HiraganaRecordEntity>
        implements FlashcardFetcher<HiraganaRecordEntity> {

    private HiraganaRecordRepository hiraganaRecordRepository;

    @Autowired
    public HiraganaFetcher(HiraganaRecordRepository hiraganaRecordRepository) {
        this.hiraganaRecordRepository = hiraganaRecordRepository;
    }

    @Override
    public List<HiraganaRecordEntity> getGroup1(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.getRecordsBetweenGroup1(userEntity);
    }

    @Override
    public List<HiraganaRecordEntity> getGroup2(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.getRecordsBetweenGroup2(userEntity);
    }

    @Override
    public List<HiraganaRecordEntity> getGroup3(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.getRecordsBetweenGroup3(userEntity);
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.HIRAGANA.equals(type);
    }

}
