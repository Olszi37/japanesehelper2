package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyRecordEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface VocabularyRecordRepository extends JpaRepository<VocabularyRecordEntity, Long> {

    Optional<VocabularyRecordEntity> findOneByVocabularyIdAndUserId(Long vocabularyId, Long userId);

    @Query("SELECT r FROM VocabularyRecordEntity r WHERE r.weight >= 0.0 AND r.weight < 0.4 AND r.vocabulary.level = :level AND r.user = :user")
    List<VocabularyRecordEntity> getRecordsBetweenGroup1(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    @Query("SELECT r FROM VocabularyRecordEntity r WHERE r.weight >= 0.4 AND r.weight <= 0.7 AND r.vocabulary.level = :level AND r.user = :user")
    List<VocabularyRecordEntity> getRecordsBetweenGroup2(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    @Query("SELECT r FROM VocabularyRecordEntity r WHERE r.weight > 0.7 AND r.weight <= 1.0 AND r.vocabulary.level = :level AND r.user = :user")
    List<VocabularyRecordEntity> getRecordsBetweenGroup3(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM VocabularyRecordEntity r WHERE r.weight = 0.0 AND r.user = :user AND r.vocabulary.level = :level")
    int countUntouchedRecords(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM VocabularyRecordEntity r WHERE r.weight > 0.0 AND r.weight < 0.4 AND r.user = :user AND r.vocabulary.level = :level")
    int countWeakKnownRecords(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM VocabularyRecordEntity r WHERE r.weight > 0.7 AND r.weight < 1.0 AND r.user = :user AND r.vocabulary.level = :level")
    int countWellKnownRecords(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM VocabularyRecordEntity r WHERE r.weight = 1.0 AND r.user = :user AND r.vocabulary.level = :level")
    int countMasteredRecords(@Param("level") JLPTLevel level, @Param("user") UserEntity userEntity);

    Optional<VocabularyRecordEntity> findFirstByUserAndVocabularyLevel(UserEntity userEntity, JLPTLevel level);
}
