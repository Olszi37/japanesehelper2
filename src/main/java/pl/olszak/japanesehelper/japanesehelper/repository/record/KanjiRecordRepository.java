package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiRecordEntity;

import java.util.List;
import java.util.Optional;

@Repository("kanjiRecord")
public interface KanjiRecordRepository extends JpaRepository<KanjiRecordEntity, Long> {

    Optional<KanjiRecordEntity> findOneByKanjiIdAndUserId(Long kanjiId, Long userId);

    @Query("SELECT r FROM KanjiRecordEntity r WHERE r.weight >= 0.0 AND r.weight < 0.4 AND r.kanji.level = :level")
    List<KanjiRecordEntity> getRecordsBetweenGroup1(@Param("level") JLPTLevel level);

    @Query("SELECT r FROM KanjiRecordEntity r WHERE r.weight >= 0.4 AND r.weight <= 0.7 AND r.kanji.level = :level")
    List<KanjiRecordEntity> getRecordsBetweenGroup2(@Param("level") JLPTLevel level);

    @Query("SELECT r FROM KanjiRecordEntity r WHERE r.weight > 0.7 AND r.weight <= 1.0 AND r.kanji.level = :level")
    List<KanjiRecordEntity> getRecordsBetweenGroup3(@Param("level") JLPTLevel level);
}
