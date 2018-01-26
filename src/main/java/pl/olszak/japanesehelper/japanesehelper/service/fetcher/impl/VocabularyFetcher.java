package pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.repository.record.VocabularyRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.Fetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;

import java.util.List;

@Component
public class VocabularyFetcher extends Fetcher<VocabularyRecordEntity>
        implements FlashcardFetcher<VocabularyRecordEntity> {

    private final VocabularyRecordRepository vocabularyRecordRepository;

    @Autowired
    public VocabularyFetcher(VocabularyRecordRepository vocabularyRecordRepository) {
        this.vocabularyRecordRepository = vocabularyRecordRepository;
    }

    @Override
    public List<VocabularyRecordEntity> getGroup1(JLPTLevel level, UserEntity userEntity) {
        return vocabularyRecordRepository.getRecordsBetweenGroup1(level, userEntity);
    }

    @Override
    public List<VocabularyRecordEntity> getGroup2(JLPTLevel level, UserEntity userEntity) {
        return vocabularyRecordRepository.getRecordsBetweenGroup2(level, userEntity);
    }

    @Override
    public List<VocabularyRecordEntity> getGroup3(JLPTLevel level, UserEntity userEntity) {
        return vocabularyRecordRepository.getRecordsBetweenGroup3(level, userEntity);
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.VOCABULARY.equals(type);
    }
}
