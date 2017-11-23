package pl.olszak.japanesehelper.japanesehelper.repository.katakana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaRecordEntity;

@Repository
public interface KatakanaRecordRepository extends JpaRepository<KatakanaRecordEntity, Long>{
}
