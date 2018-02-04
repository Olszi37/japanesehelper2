package pl.olszak.japanesehelper.japanesehelper.service.record;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.StatsDTO;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.security.SecurityUtils;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Set;

public abstract class AbstractRecordService<RECORD_ENTITY extends RecordEntity, DTO extends FlashcardDTO, ENTITY extends EntityInterface> {

    private final UserService userService;

    public AbstractRecordService(UserService userService) {
        this.userService = userService;
    }

    public void save(List<UserRecordDTO> recordDTOs) {
        List<RECORD_ENTITY> records = Lists.newArrayList();
        recordDTOs.forEach(record -> records.add(calculateRecord(record)));
        saveRecords(records);
    }

    private RECORD_ENTITY calculateRecord(UserRecordDTO recordDTO) {
        RECORD_ENTITY RECORDEntity = getRecord(recordDTO.getRecordId());
        RECORDEntity.calculateWeight(recordDTO.isCorrect());
        return RECORDEntity;
    }

    public List<DTO> getFlashcards(JLPTLevel level, int flashcardQuantity) {
        UserEntity user = userService.findByLogin(SecurityUtils.getCurrentLoggedUserLogin())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        createRecordsIfHiraganaRecordsForUserAreEmpty(user, level);

        List<RECORD_ENTITY> records = getRecords(level, user);

        return createFlashcards(records);
    }

    public abstract List<RECORD_ENTITY> getRecords(JLPTLevel level, UserEntity userEntity);

    private List<DTO> createFlashcards(List<RECORD_ENTITY> records) {
        List<DTO> flashcards = Lists.newArrayList();

        records.forEach(record -> {
            DTO flashcard = createFlashcard(record);
            flashcards.add(flashcard);
        });

        return flashcards;
    }

    public abstract DTO createFlashcard(RECORD_ENTITY record);

    public List<ENTITY> getOthers(Long id, JLPTLevel level) {
        List<ENTITY> all = getEntities(level);
        Set<ENTITY> selected = Sets.newHashSet();

        Random random = new Random();

        while(selected.size() < 5) {
            int i = random.nextInt(all.size());
            ENTITY entity = all.get(i);
            if (!entity.getId().equals(id)) {
                selected.add(entity);
            }
        }
        return Lists.newArrayList(selected);
    }

    private void createRecordsIfHiraganaRecordsForUserAreEmpty(UserEntity user, JLPTLevel level) {
        if (!checkIfRecordsExistsForUser(user, level)) {
            saveEmptyRecords(user, level);
        }
    }

    public abstract boolean checkIfRecordsExistsForUser(UserEntity userEntity, JLPTLevel level);

    private void saveEmptyRecords(UserEntity user, JLPTLevel level) {
        List<ENTITY> entities = getEntities(level);

        List<RECORD_ENTITY> recordEntities = Lists.newArrayList();

        entities.forEach(entity -> {
            recordEntities.add(createNewRecord(entity, user));
        });

        saveRecords(recordEntities);
    }

    public abstract List<ENTITY> getEntities(JLPTLevel level);

    public abstract RECORD_ENTITY createNewRecord(ENTITY entity, UserEntity userEntity);

    public abstract RECORD_ENTITY getRecord(Long id);

    public abstract void saveRecords(List<RECORD_ENTITY> entities);

    public StatsDTO getStats(JLPTLevel level) {
        UserEntity user = userService.findByLogin(SecurityUtils.getCurrentLoggedUserLogin())
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        StatsDTO stats = new StatsDTO();
        stats.setUntouched((long) getUntouchedRecords(level, user));
        stats.setWeakKnown((long) getWeakKnownRecords(level, user));
        stats.setMidKnown((long) getMidKnownRecords(level, user));
        stats.setWellKnown((long) getWellKnownRecords(level, user));
        stats.setMastered((long) getMasteredRecords(level, user));
        return stats;
    }

    public abstract int getUntouchedRecords(JLPTLevel level, UserEntity userEntity);
    public abstract int getWeakKnownRecords(JLPTLevel level, UserEntity userEntity);
    public abstract int getMidKnownRecords(JLPTLevel level, UserEntity userEntity);
    public abstract int getWellKnownRecords(JLPTLevel level, UserEntity userEntity);
    public abstract int getMasteredRecords(JLPTLevel level, UserEntity userEntity);
}
