package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;

import java.util.Optional;

@Repository("hiraganaRecord")
public interface HiraganaRecordRepository extends JpaRepository<HiraganaRecordEntity, Long> {

    Optional<HiraganaRecordEntity> findOneByHiraganaIdAndUserId(Long hiraganaId, Long userId);
}
