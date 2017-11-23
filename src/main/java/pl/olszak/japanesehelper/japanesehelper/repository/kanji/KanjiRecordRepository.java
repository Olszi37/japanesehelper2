package pl.olszak.japanesehelper.japanesehelper.repository.kanji;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiRecordEntity;

@Repository
public interface KanjiRecordRepository extends JpaRepository<KanjiRecordEntity, Long> {
}
