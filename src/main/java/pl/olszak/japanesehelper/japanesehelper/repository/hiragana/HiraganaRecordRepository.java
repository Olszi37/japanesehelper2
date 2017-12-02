package pl.olszak.japanesehelper.japanesehelper.repository.hiragana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface HiraganaRecordRepository extends JpaRepository<HiraganaRecordEntity, Long>{

    Optional<HiraganaRecordEntity> findFirstByUser(UserEntity userEntity);

    @Query("SELECT AVG(h.weight) FROM HiraganaRecordEntity h WHERE h.user = :user")
    double averageWeightByUser(@Param("user") UserEntity user);

    Optional<HiraganaRecordEntity> findAnyByUserAndWeightBetween(UserEntity userEntity,
                                                                 BigDecimal min, BigDecimal max);
}
