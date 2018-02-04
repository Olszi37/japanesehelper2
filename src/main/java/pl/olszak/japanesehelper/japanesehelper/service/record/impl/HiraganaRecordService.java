package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.hiragana.HiraganaConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.KanaFlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.record.HiraganaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.factory.FetcherFactory;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl.HiraganaFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.hiragana.HiraganaService;
import pl.olszak.japanesehelper.japanesehelper.service.record.AbstractRecordService;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HiraganaRecordService extends AbstractRecordService<HiraganaRecordEntity, KanaFlashcardDTO, HiraganaEntity>
        implements RecordService<KanaFlashcardDTO>{

    private HiraganaService hiraganaService;
    private HiraganaRecordRepository hiraganaRecordRepository;
    private HiraganaConverter hiraganaConverter;
    private UserService userService;
    private FetcherFactory fetcherFactory;

    @Autowired
    public HiraganaRecordService(HiraganaService hiraganaService,
                                 HiraganaRecordRepository hiraganaRecordRepository,
                                 HiraganaConverter hiraganaConverter, UserService userService,
                                 FetcherFactory fetcherFactory) {
        super(userService);
        this.hiraganaService = hiraganaService;
        this.hiraganaRecordRepository = hiraganaRecordRepository;
        this.hiraganaConverter = hiraganaConverter;
        this.userService = userService;
        this.fetcherFactory = fetcherFactory;
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.HIRAGANA.equals(type);
    }

    @Override
    public List<HiraganaRecordEntity> getRecords(JLPTLevel level, UserEntity userEntity) {
        FlashcardFetcher fetcher = fetcherFactory.getFetcher(FlashcardType.HIRAGANA);
        return ((HiraganaFetcher)fetcher).getFlashcards(level, userEntity);
    }

    @Override
    public KanaFlashcardDTO createFlashcard(HiraganaRecordEntity record) {
        KanaFlashcardDTO kanaFlashcard = new KanaFlashcardDTO();
        kanaFlashcard.setRecordId(record.getId());
        kanaFlashcard.setCorrect(hiraganaConverter.convertToDTO(record.getHiragana()));
        kanaFlashcard.setOther(getOthers(record.getHiragana().getId(), null).stream()
                .map(hiraganaConverter::convertToDTO).collect(Collectors.toList()));
        return kanaFlashcard;
    }

    @Override
    public boolean checkIfRecordsExistsForUser(UserEntity userEntity, JLPTLevel level) {
        return hiraganaRecordRepository.findFirstByUser(userEntity).isPresent();
    }

    @Override
    public List<HiraganaEntity> getEntities(JLPTLevel level) {
        return hiraganaService.findAllEntities();
    }

    @Override
    public HiraganaRecordEntity createNewRecord(HiraganaEntity hiragana, UserEntity userEntity) {
        HiraganaRecordEntity entity = new HiraganaRecordEntity();
        entity.setUser(userEntity);
        entity.setHiragana(hiragana);
        return entity;
    }

    @Override
    public HiraganaRecordEntity getRecord(Long id) {
        return hiraganaRecordRepository.findOne(id);
    }

    @Override
    public void saveRecords(List<HiraganaRecordEntity> hiraganaRecordEntities) {
        hiraganaRecordRepository.save(hiraganaRecordEntities);
    }

    @Override
    public int getUntouchedRecords(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.countUntouchedRecords(userEntity);
    }

    @Override
    public int getWeakKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.countWeakKnownRecords(userEntity);
    }

    @Override
    public int getMidKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.getRecordsBetweenGroup2(userEntity).size();
    }

    @Override
    public int getWellKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.countWellKnownRecords(userEntity);
    }

    @Override
    public int getMasteredRecords(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.countMasteredRecords(userEntity);
    }
}
