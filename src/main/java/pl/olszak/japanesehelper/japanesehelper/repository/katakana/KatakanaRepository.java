package pl.olszak.japanesehelper.japanesehelper.repository.katakana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaEntity;

@Repository
public interface KatakanaRepository extends JpaRepository<KatakanaEntity, Long> {
}
