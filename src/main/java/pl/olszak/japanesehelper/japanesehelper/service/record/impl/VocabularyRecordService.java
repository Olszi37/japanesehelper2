package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.vocabulary.VocabularyConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.VocabularyFlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.record.VocabularyRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.factory.FetcherFactory;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl.VocabularyFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.record.AbstractRecordService;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;
import pl.olszak.japanesehelper.japanesehelper.service.vocabulary.VocabularyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VocabularyRecordService extends AbstractRecordService<VocabularyRecordEntity, VocabularyFlashcardDTO, VocabularyEntity> implements RecordService<VocabularyFlashcardDTO> {

    private VocabularyService vocabularyService;
    private VocabularyRecordRepository vocabularyRecordRepository;
    private UserService userService;
    private FetcherFactory fetcherFactory;
    private VocabularyConverter vocabularyConverter;

    @Autowired
    public VocabularyRecordService(VocabularyService vocabularyService, VocabularyRecordRepository vocabularyRecordRepository, UserService userService, FetcherFactory fetcherFactory, VocabularyConverter vocabularyConverter) {
        super(userService);
        this.vocabularyService = vocabularyService;
        this.vocabularyRecordRepository = vocabularyRecordRepository;
        this.userService = userService;
        this.fetcherFactory = fetcherFactory;
        this.vocabularyConverter = vocabularyConverter;
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.VOCABULARY.equals(type);
    }

    @Override
    public List<VocabularyRecordEntity> getRecords(JLPTLevel level, UserEntity userEntity) {
        FlashcardFetcher fetcher = fetcherFactory.getFetcher(FlashcardType.VOCABULARY);
        return ((VocabularyFetcher)fetcher).getFlashcards(level, userEntity);
    }

    @Override
    public VocabularyFlashcardDTO createFlashcard(VocabularyRecordEntity record) {
        VocabularyFlashcardDTO flashcardDTO = new VocabularyFlashcardDTO();
        flashcardDTO.setRecordId(record.getId());
        flashcardDTO.setCorrect(vocabularyConverter.convertToDTO(record.getVocabulary()));
        flashcardDTO.setOther(getOthers(record.getVocabulary().getId(), record.getVocabulary().getLevel())
                .stream().map(vocabularyConverter::convertToDTO).collect(Collectors.toList()));
        return flashcardDTO;
    }

    @Override
    public boolean checkIfRecordsExistsForUser(UserEntity userEntity, JLPTLevel level) {
        return vocabularyRecordRepository.findFirstByUserAndVocabularyLevel(userEntity, level).isPresent();
    }

    @Override
    public List<VocabularyEntity> getEntities(JLPTLevel level) {
        return vocabularyService.findAllEntitiesByLevel(level);
    }

    @Override
    public VocabularyRecordEntity createNewRecord(VocabularyEntity vocabularyEntity, UserEntity userEntity) {
        VocabularyRecordEntity entity = new VocabularyRecordEntity();
        entity.setUser(userEntity);
        entity.setVocabulary(vocabularyEntity);
        return entity;
    }

    @Override
    public VocabularyRecordEntity getRecord(Long id) {
        return vocabularyRecordRepository.findOne(id);
    }

    @Override
    public void saveRecords(List<VocabularyRecordEntity> vocabularyRecordEntities) {
        vocabularyRecordRepository.save(vocabularyRecordEntities);
    }

    @Override
    public int getUntouchedRecords(JLPTLevel level, UserEntity userEntity) {
        return vocabularyRecordRepository.countUntouchedRecords(level, userEntity);
    }

    @Override
    public int getWeakKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return vocabularyRecordRepository.countWeakKnownRecords(level, userEntity);
    }

    @Override
    public int getMidKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return vocabularyRecordRepository.getRecordsBetweenGroup2(level, userEntity).size();
    }

    @Override
    public int getWellKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return vocabularyRecordRepository.countWellKnownRecords(level, userEntity);
    }

    @Override
    public int getMasteredRecords(JLPTLevel level, UserEntity userEntity) {
        return vocabularyRecordRepository.countMasteredRecords(level, userEntity);
    }
}
