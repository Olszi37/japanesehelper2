package pl.olszak.japanesehelper.japanesehelper.repository.katakana;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface KatakanaRecordRepository extends JpaRepository<KatakanaRecordEntity, Long>{

    Optional<KatakanaRecordEntity> findFirstByUser(UserEntity userEntity);

    @Query("SELECT AVG(k.weight) FROM KatakanaRecordEntity k WHERE k.user = :user")
    double averageWeightByUser(@Param("user") UserEntity user);

    List<KatakanaRecordEntity> findByUserAndWeightBetween(UserEntity userEntity, BigDecimal min, BigDecimal max, Pageable pageable);
}
