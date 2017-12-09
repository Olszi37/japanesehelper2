package pl.olszak.japanesehelper.japanesehelper.repository.kanji;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface KanjiRecordRepository extends JpaRepository<KanjiRecordEntity, Long> {

    Optional<KanjiRecordEntity> findFirstByUser(UserEntity userEntity);

    @Query("SELECT AVG(k.weight) FROM KanjiRecordEntity k WHERE k.user = :user AND k.kanji.level = :level")
    double averageWeightByUser(@Param("user") UserEntity user, @Param("level") JLPTLevel level);

    List<KanjiRecordEntity> findByUserAndWeightBetweenAndKanjiLevel(UserEntity userEntity, BigDecimal min, BigDecimal max, Pageable pageable, JLPTLevel level);
}
