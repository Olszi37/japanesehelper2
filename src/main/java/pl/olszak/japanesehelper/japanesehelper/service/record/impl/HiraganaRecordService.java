package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.hiragana.HiraganaConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.KanaFlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.hiragana.HiraganaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.security.SecurityUtils;
import pl.olszak.japanesehelper.japanesehelper.service.hiragana.HiraganaService;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static pl.olszak.japanesehelper.japanesehelper.domain.enumerated.MasteryLevel.*;

@Service
@Transactional
public class HiraganaRecordService implements RecordService<KanaFlashcardDTO>{

    private HiraganaService hiraganaService;
    @Qualifier("hiraganaRecord")
    private HiraganaRecordRepository hiraganaRecordRepository;
    private HiraganaConverter hiraganaConverter;
    private UserService userService;

    @Autowired
    public HiraganaRecordService(HiraganaService hiraganaService,
                                 HiraganaRecordRepository hiraganaRecordRepository,
                                 HiraganaConverter hiraganaConverter, UserService userService) {
        this.hiraganaService = hiraganaService;
        this.hiraganaRecordRepository = hiraganaRecordRepository;
        this.hiraganaConverter = hiraganaConverter;
        this.userService = userService;
    }

    @Override
    public void save(List<UserRecordDTO> recordDTOs) {
        List<HiraganaRecordEntity> hiraganaRecords = Lists.newArrayList();
        recordDTOs.forEach(record -> hiraganaRecords.add(calculateRecord(record)));
        hiraganaRecordRepository.save(hiraganaRecords);
    }

    private HiraganaRecordEntity calculateRecord(UserRecordDTO recordDTO){
        HiraganaRecordEntity entity = getRecord(recordDTO.getRecordId());
        entity.calculateWeight(recordDTO.isCorrect());
        return entity;
    }

    private HiraganaRecordEntity getRecord(Long id){
        return hiraganaRecordRepository.findOne(id);
    }

    @Override
    public List<KanaFlashcardDTO> getFlashcards(JLPTLevel level, int flashcardQuantity) {
        UserEntity user = userService.findByLogin(SecurityUtils.getCurrentLoggedUserLogin()).orElseThrow(() -> new EntityNotFoundException("User not found!"));

        createRecordsIfHiraganaRecordsForUserAreEmpty(user);

        List<HiraganaRecordEntity> records = getRecordsInLearningScope(user, flashcardQuantity);

        return createFlashcards(records);
    }

    private void createRecordsIfHiraganaRecordsForUserAreEmpty(UserEntity user){
        if(!hiraganaRecordRepository.findFirstByUser(user).isPresent()){
            saveEmptyRecords(user);
        }
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

//    private List<HiraganaRecordEntity> getRecordsInLearningScope(UserEntity user, int flashcardQuantity){
//        double averageWeight = hiraganaRecordRepository.averageWeightByUser(user);
//        BigDecimal weightDown = new BigDecimal(averageWeight - 0.2);
//        BigDecimal weightUp = new BigDecimal(averageWeight + 0.2);
//
//        Pageable pageable = new PageRequest(0, flashcardQuantity);
//        return hiraganaRecordRepository.findByUserAndWeightBetween(user, weightDown, weightUp, pageable);
//    }

    private List<HiraganaRecordEntity> getRecordsInLearningScope(UserEntity user, int flashcardQuantity){
        List<HiraganaRecordEntity> unknown = hiraganaRecordRepository.findByUserAndWieghtBetween2(user, UKNOWN);
        List<HiraganaRecordEntity> weak = hiraganaRecordRepository.findByUserAndWieghtBetween2(user, WEAK);
        List<HiraganaRecordEntity> intermediate = hiraganaRecordRepository.findByUserAndWieghtBetween2(user, INTERMEDIATE);
        List<HiraganaRecordEntity> wellKnown = hiraganaRecordRepository.findByUserAndWieghtBetween2(user, WELL_KNOWN);
        List<HiraganaRecordEntity> mastered = hiraganaRecordRepository.findByUserAndWieghtBetween2(user, MASTERED);

        return null;
    }

    private List<KanaFlashcardDTO> createFlashcards(List<HiraganaRecordEntity> records){
        List<KanaFlashcardDTO> kanaFlashcards = Lists.newArrayList();

        records.forEach(record -> {
            KanaFlashcardDTO kanaFlashcard = new KanaFlashcardDTO();
            kanaFlashcard.setCorrect(hiraganaConverter.convertToDTO(record.getHiragana()));
            kanaFlashcard.setOther(hiraganaService.getOther5Entities(record.getHiragana().getId()).stream().map(hiraganaConverter::convertToDTO).collect(Collectors.toList()));
            kanaFlashcards.add(kanaFlashcard);
        });

        return kanaFlashcards;
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.HIRAGANA.equals(type);
    }
}
