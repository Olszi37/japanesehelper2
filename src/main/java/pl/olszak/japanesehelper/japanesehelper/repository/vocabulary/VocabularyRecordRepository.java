package pl.olszak.japanesehelper.japanesehelper.repository.vocabulary;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyRecordEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface VocabularyRecordRepository extends JpaRepository<VocabularyRecordEntity, Long>{

    Optional<VocabularyRecordEntity> findFirstByUser(UserEntity userEntity);

    @Query("SELECT AVG(v.weight) FROM VocabularyRecordEntity v WHERE v.user = :user AND v.vocabulary.level = :level")
    double averageWeightByUser(@Param("user") UserEntity user, @Param("level") JLPTLevel level);

    List<VocabularyRecordEntity> findByUserAndWeightBetweenAndVocabularyLevel(UserEntity userEntity, BigDecimal min, BigDecimal max, Pageable pageable, JLPTLevel level);

    @Query("SELECT r FROM VocabularyRecordEntity r WHERE r.weight >= 0.0 AND r.weight < 0.4 AND r.vocabulary.level = :level")
    List<VocabularyRecordEntity> getRecordsBetweenGroup1(@Param("level") JLPTLevel level);

    @Query("SELECT r FROM VocabularyRecordEntity r WHERE r.weight >= 0.4 AND r.weight <= 0.7 AND r.vocabulary.level = :level")
    List<VocabularyRecordEntity> getRecordsBetweenGroup2(@Param("level") JLPTLevel level);

    @Query("SELECT r FROM VocabularyRecordEntity r WHERE r.weight > 0.7 AND r.weight <= 1.0 AND r.vocabulary.level = :level")
    List<VocabularyRecordEntity> getRecordsBetweenGroup3(@Param("level") JLPTLevel level);
}
