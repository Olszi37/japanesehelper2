package pl.olszak.japanesehelper.japanesehelper.service.record.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.kanji.KanjiConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.dto.record.KanjiFlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.record.KanjiRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.factory.FetcherFactory;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl.KanjiFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.kanji.KanjiService;
import pl.olszak.japanesehelper.japanesehelper.service.record.AbstractRecordService;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KanjiRecordService extends AbstractRecordService<KanjiRecordEntity, KanjiFlashcardDTO, KanjiEntity> implements RecordService<KanjiFlashcardDTO>{

    private KanjiService kanjiService;
    private KanjiRecordRepository kanjiRecordRepository;
    private UserService userService;
    private FetcherFactory fetcherFactory;
    private KanjiConverter kanjiConverter;

    @Autowired
    public KanjiRecordService(KanjiService kanjiService, KanjiRecordRepository kanjiRecordRepository, UserService userService, FetcherFactory fetcherFactory, KanjiConverter kanjiConverter) {
        super(userService);
        this.kanjiService = kanjiService;
        this.kanjiRecordRepository = kanjiRecordRepository;
        this.userService = userService;
        this.fetcherFactory = fetcherFactory;
        this.kanjiConverter = kanjiConverter;
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.KANJI.equals(type);
    }

    @Override
    public List<KanjiRecordEntity> getRecords(JLPTLevel level, UserEntity userEntity) {
        FlashcardFetcher fetcher = fetcherFactory.getFetcher(FlashcardType.KANJI);
        return ((KanjiFetcher)fetcher).getFlashcards(level, userEntity);
    }

    @Override
    public KanjiFlashcardDTO createFlashcard(KanjiRecordEntity record) {
        KanjiFlashcardDTO flashcardDTO = new KanjiFlashcardDTO();
        flashcardDTO.setRecordId(record.getId());
        flashcardDTO.setCorrect(kanjiConverter.convertToDTO(record.getKanji()));
        flashcardDTO.setOther(getOthers(record.getKanji().getId(), record.getKanji().getLevel()).stream()
                .map(kanjiConverter::convertToDTO).collect(Collectors.toList()));
        return null;
    }

    @Override
    public boolean checkIfRecordsExistsForUser(UserEntity userEntity, JLPTLevel level) {
        return kanjiRecordRepository.findFirstByUserAndKanjiLevel(userEntity, level).isPresent();
    }

    @Override
    public List<KanjiEntity> getEntities(JLPTLevel level) {
        return kanjiService.findByLevelEntities(level);
    }

    @Override
    public KanjiRecordEntity createNewRecord(KanjiEntity kanjiEntity, UserEntity userEntity) {
        KanjiRecordEntity entity = new KanjiRecordEntity();
        entity.setUser(userEntity);
        entity.setKanji(kanjiEntity);
        return entity;
    }

    @Override
    public KanjiRecordEntity getRecord(Long id) {
        return kanjiRecordRepository.findOne(id);
    }

    @Override
    public void saveRecords(List<KanjiRecordEntity> kanjiRecordEntities) {
        kanjiRecordRepository.save(kanjiRecordEntities);
    }

    @Override
    public int getUntouchedRecords(JLPTLevel level, UserEntity userEntity) {
        return kanjiRecordRepository.countUntouchedRecords(level, userEntity);
    }

    @Override
    public int getWeakKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return kanjiRecordRepository.countWeakKnownRecords(level, userEntity);
    }

    @Override
    public int getMidKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return kanjiRecordRepository.getRecordsBetweenGroup2(level, userEntity).size();
    }

    @Override
    public int getWellKnownRecords(JLPTLevel level, UserEntity userEntity) {
        return kanjiRecordRepository.countWellKnownRecords(level, userEntity);
    }

    @Override
    public int getMasteredRecords(JLPTLevel level, UserEntity userEntity) {
        return kanjiRecordRepository.countMasteredRecords(level, userEntity);
    }
}
