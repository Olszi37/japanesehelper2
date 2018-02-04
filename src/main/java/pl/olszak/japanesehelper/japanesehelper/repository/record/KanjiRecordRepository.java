package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface KanjiRecordRepository extends JpaRepository<KanjiRecordEntity, Long> {

    Optional<KanjiRecordEntity> findOneByKanjiIdAndUserId(Long kanjiId, Long userId);

    @Query("SELECT r FROM KanjiRecordEntity r WHERE r.weight >= 0.0 AND r.weight < 0.4 AND r.kanji.level = :level AND r.user = :user")
    List<KanjiRecordEntity> getRecordsBetweenGroup1(@Param("level") JLPTLevel level, @Param("user")UserEntity userEntity);

    @Query("SELECT r FROM KanjiRecordEntity r WHERE r.weight >= 0.4 AND r.weight <= 0.7 AND r.kanji.level = :level AND r.user = :user")
    List<KanjiRecordEntity> getRecordsBetweenGroup2(@Param("level") JLPTLevel level, @Param("user")UserEntity userEntity);

    @Query("SELECT r FROM KanjiRecordEntity r WHERE r.weight > 0.7 AND r.weight <= 1.0 AND r.kanji.level = :level AND r.user = :user")
    List<KanjiRecordEntity> getRecordsBetweenGroup3(@Param("level") JLPTLevel level, @Param("user")UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM KanjiRecordEntity r WHERE r.weight = 0.0 AND r.user = :user AND r.kanji.level = :level")
    int countUntouchedRecords(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM KanjiRecordEntity r WHERE r.weight > 0.0 AND r.weight < 0.4 AND r.user = :user AND r.kanji.level = :level")
    int countWeakKnownRecords(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM KanjiRecordEntity r WHERE r.weight > 0.7 AND r.weight < 1.0 AND r.user = :user AND r.kanji.level = :level")
    int countWellKnownRecords(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM KanjiRecordEntity r WHERE r.weight = 1.0 AND r.user = :user AND r.kanji.level = :level")
    int countMasteredRecords(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    Optional<KanjiRecordEntity> findFirstByUserAndKanjiLevel(UserEntity user, JLPTLevel level);
}
