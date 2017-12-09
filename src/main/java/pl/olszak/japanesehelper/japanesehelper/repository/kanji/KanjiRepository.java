package pl.olszak.japanesehelper.japanesehelper.repository.kanji;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface KanjiRepository extends JpaRepository<KanjiEntity, Long>{

    List<KanjiEntity> findByLevel(JLPTLevel level);

    Optional<KanjiEntity> findBySign(String sign);

    List<KanjiEntity> findTop5ByIdNotAndLevel(Long id, JLPTLevel level);
}
