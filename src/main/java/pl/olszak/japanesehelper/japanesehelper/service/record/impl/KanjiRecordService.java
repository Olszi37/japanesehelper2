package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.KanjiFlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.kanji.KanjiRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.kanji.KanjiService;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KanjiRecordService implements RecordService<KanjiFlashcardDTO>{

    private KanjiService kanjiService;
    private KanjiRecordRepository kanjiRecordRepository;
    private UserService userService;

    @Autowired
    public KanjiRecordService(KanjiService kanjiService, KanjiRecordRepository kanjiRecordRepository, UserService userService) {
        this.kanjiService = kanjiService;
        this.kanjiRecordRepository = kanjiRecordRepository;
        this.userService = userService;
    }

    @Override
    public void save(List<UserRecordDTO> recordDTOs) {
//        Optional<UserEntity> user = userService.findByLogin(SecurityUtils.getCurrentLoggedUserLogin());
//        if(user.isPresent()){
//            List<KanjiRecordEntity> kanjiRecords = Lists.newArrayList();
//            recordDTO.getFlashcards().forEach(flashcard -> {
//                kanjiRecords.add(createOrCalculateRecord(flashcard, user.get()));
//            });
//            kanjiRecordRepository.save(kanjiRecords);
//        } else {
//            throw new UsernameNotFoundException("User with given login is not found!");
//        }
    }

    @Override
    public List<KanjiFlashcardDTO> getFlashcards(JLPTLevel level, int flashcardCount) {
        return null;
    }

    private KanjiRecordEntity createOrCalculateRecord(FlashcardDTO flashcardDTO, UserEntity userEntity){
//        Optional<KanjiRecordEntity> entity = getRecord(flashcardDTO.getRecordId());
//        if(entity.isPresent()){
//            KanjiRecordEntity updatedEntity = entity.get();
//            updatedEntity.calculateWeight(flashcardDTO.isSuccess());
//            return updatedEntity;
//        } else {
//            KanjiRecordEntity newEntity = new KanjiRecordEntity();
//            newEntity.setUser(userEntity);
//            newEntity.setKanji(kanjiService.findOneEntity(flashcardDTO.getId()));
//            newEntity.calculateWeight(flashcardDTO.isSuccess());
//            return newEntity;
//        }
        return null;
    }

    private Optional<KanjiRecordEntity> getRecord(Long id){
        return Optional.ofNullable(kanjiRecordRepository.findOne(id));
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.KANJI.equals(type);
    }
}
