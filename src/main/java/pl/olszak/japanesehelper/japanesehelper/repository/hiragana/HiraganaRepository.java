package pl.olszak.japanesehelper.japanesehelper.repository.hiragana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;

import java.util.List;

@Repository
public interface HiraganaRepository extends JpaRepository<HiraganaEntity, Long> {

    List<HiraganaEntity> findTop5ByIdNot(Long id);
}
