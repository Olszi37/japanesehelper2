package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.katakana.KatakanaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.security.SecurityUtils;
import pl.olszak.japanesehelper.japanesehelper.service.katakana.KatakanaService;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class KatakanaRecordService implements RecordService{

    private KatakanaService katakanaService;
    private KatakanaRecordRepository katakanaRecordRepository;
    private UserService userService;

    @Autowired
    public KatakanaRecordService(KatakanaService katakanaService, KatakanaRecordRepository katakanaRecordRepository, UserService userService) {
        this.katakanaService = katakanaService;
        this.katakanaRecordRepository = katakanaRecordRepository;
        this.userService = userService;
    }

    @Override
    public void save(UserRecordDTO recordDTO) {
        Optional<UserEntity> user = userService.findByLogin(SecurityUtils.getCurrentLoggedUserLogin());
        if(user.isPresent()){
            List<KatakanaRecordEntity> katakanaRecords = Lists.newArrayList();
            recordDTO.getFlashcards().forEach(flashcard -> {
                katakanaRecords.add(createOrCalculateRecord(flashcard, user.get()));
            });
            katakanaRecordRepository.save(katakanaRecords);
        } else {
            throw new UsernameNotFoundException("User with given login is not found!");
        }
    }

    @Override
    public List<Object> getFlashcards(JLPTLevel level, int flashcardCount) {
        return null;
    }

    @Override
    public Object getFlashcard(JLPTLevel level) {
        return null;
    }

    private KatakanaRecordEntity createOrCalculateRecord(FlashcardDTO flashcardDTO, UserEntity userEntity){
        Optional<KatakanaRecordEntity> entity = getRecord(flashcardDTO.getRecordId());
        if(entity.isPresent()){
            KatakanaRecordEntity updatedEntity = entity.get();
            updatedEntity.calculateWeight(flashcardDTO.isSuccess());
            return updatedEntity;
        } else {
            KatakanaRecordEntity newEntity = new KatakanaRecordEntity();
            newEntity.setUser(userEntity);
            newEntity.setKatakana(katakanaService.findOneEntity(flashcardDTO.getId()));
            newEntity.calculateWeight(flashcardDTO.isSuccess());
            return newEntity;
        }
    }

    private Optional<KatakanaRecordEntity> getRecord(Long id){
        return Optional.ofNullable(katakanaRecordRepository.findOne(id));
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.KATAKANA.equals(type);
    }
}
