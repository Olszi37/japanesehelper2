package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.record.RecordRepository;
import pl.olszak.japanesehelper.japanesehelper.security.SecurityUtils;
import pl.olszak.japanesehelper.japanesehelper.service.hiragana.HiraganaService;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HiraganaRecordService implements RecordService{

    private HiraganaService hiraganaService;
    private RecordRepository<HiraganaRecordEntity, Long> recordRepository;
    private UserService userService;

    @Autowired
    public HiraganaRecordService(HiraganaService hiraganaService,
                                 RecordRepository<HiraganaRecordEntity, Long> recordRepository,
                                 UserService userService) {
        this.hiraganaService = hiraganaService;
        this.recordRepository = recordRepository;
        this.userService = userService;
    }

    @Override
    public void save(UserRecordDTO recordDTO) {
        Optional<UserEntity> user = userService.findByLogin(SecurityUtils.getCurrentLoggedUserLogin());
        if(user.isPresent()){
            List<HiraganaRecordEntity> hiraganaRecords = Lists.newArrayList();
            recordDTO.getFlashcards().forEach(flashcard -> {
                //TODO getting existing records
                HiraganaRecordEntity entity = new HiraganaRecordEntity();
                entity.setUser(user.get());
                entity.setHiragana(hiraganaService.findOneEntity(flashcard.getId()));
                entity.calculateWeight(flashcard.isSuccess());
                hiraganaRecords.add(entity);
            });
            recordRepository.save(hiraganaRecords);
        } else {
            throw new UsernameNotFoundException("User with given login is not found!");
        }
    }

    private HiraganaRecordEntity getExistingRecord(){
        return null;
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.HIRAGANA.equals(type);
    }
}
