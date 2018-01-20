package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaRecordEntity;

import java.util.List;
import java.util.Optional;

@Repository("katakanaRecord")
public interface KatakanaRecordRepository extends JpaRepository<KatakanaRecordEntity, Long> {

    Optional<KatakanaRecordEntity> findOneByKatakanaIdAndUserId(Long katakanaId, Long userId);

    @Query("SELECT r FROM KatakanaRecordEntity r WHERE r.weight >= 0.0 AND r.weight < 0.4")
    List<KatakanaRecordEntity> getRecordsBetweenGroup1();

    @Query("SELECT r FROM KatakanaRecordEntity r WHERE r.weight >= 0.4 AND r.weight <= 0.7")
    List<KatakanaRecordEntity> getRecordsBetweenGroup2();

    @Query("SELECT r FROM KatakanaRecordEntity r WHERE r.weight > 0.7 AND r.weight <= 1.0")
    List<KatakanaRecordEntity> getRecordsBetweenGroup3();
}
