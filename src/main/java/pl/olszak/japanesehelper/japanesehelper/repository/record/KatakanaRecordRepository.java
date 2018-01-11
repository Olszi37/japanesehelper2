package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaRecordEntity;

import java.util.Optional;

@Repository("katakanaRecord")
public interface KatakanaRecordRepository extends JpaRepository<KatakanaRecordEntity, Long> {

    Optional<KatakanaRecordEntity> findOneByKatakanaIdAndUserId(Long katakanaId, Long userId);
}
