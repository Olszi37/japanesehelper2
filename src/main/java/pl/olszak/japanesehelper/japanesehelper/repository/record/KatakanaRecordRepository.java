package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface KatakanaRecordRepository extends JpaRepository<KatakanaRecordEntity, Long> {

    @Query("SELECT r FROM KatakanaRecordEntity r WHERE r.weight >= 0.0 AND r.weight < 0.4 AND r.user = :user")
    List<KatakanaRecordEntity> getRecordsBetweenGroup1(@Param("user") UserEntity userEntity);

    @Query("SELECT r FROM KatakanaRecordEntity r WHERE r.weight >= 0.4 AND r.weight <= 0.7 AND r.user = :user")
    List<KatakanaRecordEntity> getRecordsBetweenGroup2(@Param("user") UserEntity userEntity);

    @Query("SELECT r FROM KatakanaRecordEntity r WHERE r.weight > 0.7 AND r.weight <= 1.0 AND r.user = :user")
    List<KatakanaRecordEntity> getRecordsBetweenGroup3(@Param("user") UserEntity userEntity);

    Optional<KatakanaRecordEntity> findFirstByUser(UserEntity userEntity);
}
