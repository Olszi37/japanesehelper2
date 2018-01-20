package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;

import java.util.List;
import java.util.Optional;

@Repository("hiraganaRecord")
public interface HiraganaRecordRepository extends JpaRepository<HiraganaRecordEntity, Long> {

    Optional<HiraganaRecordEntity> findOneByHiraganaIdAndUserId(Long hiraganaId, Long userId);

    @Query("SELECT SUM(h.weight) FROM HiraganaRecordEntity h")
    double getSumOfWeights();

    @Query("SELECT r FROM HiraganaRecordEntity r WHERE r.weight >= 0.0 AND r.weight < 0.4")
    List<HiraganaRecordEntity> getRecordsBetweenGroup1();

    @Query("SELECT r FROM HiraganaRecordEntity r WHERE r.weight >= 0.4 AND r.weight <= 0.7")
    List<HiraganaRecordEntity> getRecordsBetweenGroup2();

    @Query("SELECT r FROM HiraganaRecordEntity r WHERE r.weight > 0.7 AND r.weight <= 1.0")
    List<HiraganaRecordEntity> getRecordsBetweenGroup3();
}
