package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.katakana.KatakanaConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.KanaFlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.record.KatakanaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.factory.FetcherFactory;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl.KatakanaFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.katakana.KatakanaService;
import pl.olszak.japanesehelper.japanesehelper.service.record.AbstractRecordService;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KatakanaRecordService extends AbstractRecordService<KatakanaRecordEntity, KanaFlashcardDTO, KatakanaEntity> implements RecordService<KanaFlashcardDTO>{

    private KatakanaService katakanaService;
    private KatakanaRecordRepository katakanaRecordRepository;
    private UserService userService;
    private FetcherFactory fetcherFactory;
    private KatakanaConverter katakanaConverter;

    @Autowired
    public KatakanaRecordService(KatakanaService katakanaService, KatakanaRecordRepository katakanaRecordRepository, UserService userService, FetcherFactory fetcherFactory, KatakanaConverter katakanaConverter) {
        super(userService);
        this.katakanaService = katakanaService;
        this.katakanaRecordRepository = katakanaRecordRepository;
        this.userService = userService;
        this.fetcherFactory = fetcherFactory;
        this.katakanaConverter = katakanaConverter;
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.KATAKANA.equals(type);
    }

    @Override
    public List<KatakanaRecordEntity> getRecords(JLPTLevel level, UserEntity userEntity) {
        FlashcardFetcher fetcher = fetcherFactory.getFetcher(FlashcardType.KATAKANA);
        return ((KatakanaFetcher)fetcher).getFlashcards(level, userEntity);
    }

    @Override
    public KanaFlashcardDTO createFlashcard(KatakanaRecordEntity record) {
        KanaFlashcardDTO kanaFlashcardDTO = new KanaFlashcardDTO();
        kanaFlashcardDTO.setRecordId(record.getId());
        kanaFlashcardDTO.setCorrect(katakanaConverter.convertToDTO(record.getKatakana()));
        kanaFlashcardDTO.setOther(getOthers(record.getKatakana().getId(), null).stream()
                .map(katakanaConverter::convertToDTO).collect(Collectors.toList()));
        return kanaFlashcardDTO;
    }

    @Override
    public boolean checkIfRecordsExistsForUser(UserEntity userEntity, JLPTLevel level) {
        return katakanaRecordRepository.findFirstByUser(userEntity).isPresent();
    }

    @Override
    public List<KatakanaEntity> getEntities(JLPTLevel level) {
        return katakanaService.findAllEntities();
    }

    @Override
    public KatakanaRecordEntity createNewRecord(KatakanaEntity katakanaEntity, UserEntity userEntity) {
        KatakanaRecordEntity entity = new KatakanaRecordEntity();
        entity.setUser(userEntity);
        entity.setKatakana(katakanaEntity);
        return entity;
    }

    @Override
    public KatakanaRecordEntity getRecord(Long id) {
        return katakanaRecordRepository.findOne(id);
    }

    @Override
    public void saveRecords(List<KatakanaRecordEntity> katakanaRecordEntities) {
        katakanaRecordRepository.save(katakanaRecordEntities);
    }

    @Override
    public int getUntouchedRecords(JLPTLevel level, UserEntity userEntity) {
        return katakanaRecordRepository.countUntouchedRecords(userEntity);
    }

    @Override
    public int getWeakKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return katakanaRecordRepository.countWeakKnownRecords(userEntity);
    }

    @Override
    public int getMidKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return katakanaRecordRepository.getRecordsBetweenGroup2(userEntity).size();
    }

    @Override
    public int getWellKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return katakanaRecordRepository.countWellKnownRecords(userEntity);
    }

    @Override
    public int getMasteredRecords(JLPTLevel level, UserEntity userEntity) {
        return katakanaRecordRepository.countMasteredRecords(userEntity);
    }
}
