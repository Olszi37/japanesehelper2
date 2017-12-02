package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.hiragana.HiraganaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.security.SecurityUtils;
import pl.olszak.japanesehelper.japanesehelper.service.hiragana.HiraganaService;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HiraganaRecordService implements RecordService{

    private HiraganaService hiraganaService;
    private HiraganaRecordRepository hiraganaRecordRepository;
    private UserService userService;

    @Autowired
    public HiraganaRecordService(HiraganaService hiraganaService,
                                 HiraganaRecordRepository hiraganaRecordRepository,
                                 UserService userService) {
        this.hiraganaService = hiraganaService;
        this.hiraganaRecordRepository = hiraganaRecordRepository;
        this.userService = userService;
    }

    @Override
    public void save(UserRecordDTO recordDTO) {
        Optional<UserEntity> user = userService.findByLogin(SecurityUtils.getCurrentLoggedUserLogin());
        if(user.isPresent()){
            List<HiraganaRecordEntity> hiraganaRecords = Lists.newArrayList();
            recordDTO.getFlashcards().forEach(flashcard -> {
                hiraganaRecords.add(createOrCalculateRecord(flashcard, user.get()));
            });
            hiraganaRecordRepository.save(hiraganaRecords);
        } else {
            throw new UsernameNotFoundException("User with given login is not found!");
        }
    }

    //TODO Another way of saving and fetching flashcards -> simultaneosly fetch and save one card

    private HiraganaRecordEntity createOrCalculateRecord(FlashcardDTO flashcardDTO, UserEntity userEntity){
        Optional<HiraganaRecordEntity> entity = getRecord(flashcardDTO.getRecordId());
        if(entity.isPresent()){
            HiraganaRecordEntity updatedEntity = entity.get();
            updatedEntity.calculateWeight(flashcardDTO.isSuccess());
            return updatedEntity;
        } else {
            HiraganaRecordEntity newEntity = new HiraganaRecordEntity();
            newEntity.setUser(userEntity);
            newEntity.setHiragana(hiraganaService.findOneEntity(flashcardDTO.getId()));
            newEntity.calculateWeight(flashcardDTO.isSuccess());
            return newEntity;
        }
    }

    private Optional<HiraganaRecordEntity> getRecord(Long id){
        return Optional.ofNullable(hiraganaRecordRepository.findOne(id));
    }

    @Override
    public List<Object> getFlashcards(JLPTLevel level, int flashCardCount) {
        UserEntity user = userService.findByLogin(SecurityUtils.getCurrentLoggedUserLogin()).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        List<HiraganaRecordEntity> records = hiraganaRecordRepository.findAll();

        if(CollectionUtils.isEmpty(records)){
            saveEmptyRecords(user);
        }

        return null;
    }

    private void saveEmptyRecords(UserEntity user){
        List<HiraganaEntity> hiraganas = hiraganaService.findAllEntities();

        List<HiraganaRecordEntity> recordEntities = Lists.newArrayList();

        hiraganas.forEach(hiragana -> {
            HiraganaRecordEntity entity = new HiraganaRecordEntity();
            entity.setUser(user);
            entity.setHiragana(hiragana);
            recordEntities.add(entity);
        });

        hiraganaRecordRepository.save(recordEntities);
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.HIRAGANA.equals(type);
    }
}
