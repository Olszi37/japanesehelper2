package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface HiraganaRecordRepository extends JpaRepository<HiraganaRecordEntity, Long> {

    @Query("SELECT r FROM HiraganaRecordEntity r WHERE r.weight >= 0.0 AND r.weight < 0.4 AND r.user = :user")
    List<HiraganaRecordEntity> getRecordsBetweenGroup1(@Param("user") UserEntity userEntity);

    @Query("SELECT r FROM HiraganaRecordEntity r WHERE r.weight >= 0.4 AND r.weight <= 0.7 AND r.user = :user")
    List<HiraganaRecordEntity> getRecordsBetweenGroup2(@Param("user") UserEntity userEntity);

    @Query("SELECT r FROM HiraganaRecordEntity r WHERE r.weight > 0.7 AND r.weight <= 1.0 AND r.user = :user")
    List<HiraganaRecordEntity> getRecordsBetweenGroup3(@Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM HiraganaRecordEntity r WHERE r.weight = 0.0 AND r.user = :user")
    int countUntouchedRecords(@Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM HiraganaRecordEntity r WHERE r.weight > 0.0 AND r.weight < 0.4 AND r.user = :user")
    int countWeakKnownRecords(@Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM HiraganaRecordEntity r WHERE r.weight > 0.7 AND r.weight < 1.0 AND r.user = :user")
    int countWellKnownRecords(@Param("user") UserEntity userEntity);

    @Query("SELECT COUNT(r) FROM HiraganaRecordEntity r WHERE r.weight = 1.0 AND r.user = :user")
    int countMasteredRecords(@Param("user") UserEntity userEntity);

    Optional<HiraganaRecordEntity> findFirstByUser(UserEntity userEntity);
}
