package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiRecordEntity;

import java.util.Optional;

@Repository("kanjiRecord")
public interface KanjiRecordRepository extends JpaRepository<KanjiRecordEntity, Long> {

    Optional<KanjiRecordEntity> findOneByKanjiIdAndUserId(Long kanjiId, Long userId);
}
