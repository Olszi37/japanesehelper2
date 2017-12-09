package pl.olszak.japanesehelper.japanesehelper.repository.katakana;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaEntity;

import java.util.List;

@Repository
public interface KatakanaRepository extends JpaRepository<KatakanaEntity, Long> {

    List<KatakanaEntity> findTop5ByIdNot(Long id);

}
