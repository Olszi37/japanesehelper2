package pl.olszak.japanesehelper.japanesehelper.repository.hiragana;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.MasteryLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface HiraganaRecordRepository extends JpaRepository<HiraganaRecordEntity, Long> {

    Optional<HiraganaRecordEntity> findFirstByUser(UserEntity userEntity);

    @Query("SELECT AVG(h.weight) FROM HiraganaRecordEntity h WHERE h.user = :user")
    double averageWeightByUser(@Param("user") UserEntity user);

    List<HiraganaRecordEntity> findByUserAndWeightBetween(UserEntity userEntity, BigDecimal min, BigDecimal max, Pageable pageable);

    @Query("SELECT h FROM HiraganaRecordEntity WHERE h.user = :user AND h.weight " +
            "BETWEEN(mastery.lowBorder, mastery.topBorder)")
    List<HiraganaRecordEntity> findByUserAndWieghtBetween2(@Param("user") UserEntity userEntity,
                                                           @Param("mastery") MasteryLevel mastery);
}
