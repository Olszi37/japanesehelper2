package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.VocabularyFlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.vocabulary.VocabularyRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;
import pl.olszak.japanesehelper.japanesehelper.service.vocabulary.VocabularyService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VocabularyRecordService implements RecordService<VocabularyFlashcardDTO> {

    private VocabularyService vocabularyService;
    @Qualifier("vocabularyRecord")
    private VocabularyRecordRepository vocabularyRecordRepository;
    private UserService userService;

    @Autowired
    public VocabularyRecordService(VocabularyService vocabularyService, VocabularyRecordRepository vocabularyRecordRepository, UserService userService) {
        this.vocabularyService = vocabularyService;
        this.vocabularyRecordRepository = vocabularyRecordRepository;
        this.userService = userService;
    }

    @Override
    public void save(List<UserRecordDTO> recordDTOs) {
//        Optional<UserEntity> user = userService.findByLogin(SecurityUtils.getCurrentLoggedUserLogin());
//        if(user.isPresent()){
//            List<VocabularyRecordEntity> vocabularyRecords = Lists.newArrayList();
//            recordDTO.getFlashcards().forEach(flashcard -> {
//                vocabularyRecords.add(createOrCalculateRecord(flashcard, user.get()));
//            });
//            vocabularyRecordRepository.save(vocabularyRecords);
//        } else {
//            throw new UsernameNotFoundException("User with given login is not found!");
//        }
    }

    @Override
    public List<VocabularyFlashcardDTO> getFlashcards(JLPTLevel level, int flashcardCount) {
        return null;
    }

    private VocabularyRecordEntity createOrCalculateRecord(FlashcardDTO flashcardDTO, UserEntity userEntity){
//        Optional<VocabularyRecordEntity> entity = getRecord(flashcardDTO.getRecordId());
//        if(entity.isPresent()){
//            VocabularyRecordEntity updatedEntity = entity.get();
//            updatedEntity.calculateWeight(flashcardDTO.isSuccess());
//            return updatedEntity;
//        } else {
//            VocabularyRecordEntity newEntity = new VocabularyRecordEntity();
//            newEntity.setUser(userEntity);
//            newEntity.setVocabulary(vocabularyService.findOneEntity(flashcardDTO.getId()));
//            newEntity.calculateWeight(flashcardDTO.isSuccess());
//            return newEntity;
//        }
        return null;
    }

    private Optional<VocabularyRecordEntity> getRecord(Long id){
        return Optional.ofNullable(vocabularyRecordRepository.findOne(id));
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.VOCABULARY.equals(type);
    }
}
