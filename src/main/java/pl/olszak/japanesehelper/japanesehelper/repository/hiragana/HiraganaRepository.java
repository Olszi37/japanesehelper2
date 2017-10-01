package pl.olszak.japanesehelper.japanesehelper.repository.hiragana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;

@Repository
public interface HiraganaRepository extends JpaRepository<HiraganaEntity, Long> {
}
