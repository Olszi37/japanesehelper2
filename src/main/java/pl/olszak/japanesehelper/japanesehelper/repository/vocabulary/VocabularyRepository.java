package pl.olszak.japanesehelper.japanesehelper.repository.vocabulary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyEntity;

import java.util.List;

@Repository
public interface VocabularyRepository extends JpaRepository<VocabularyEntity, Long>{

    List<VocabularyEntity> findTop5ByIdNotAndLevel(Long id, JLPTLevel level);

    List<VocabularyEntity> findByLevel(JLPTLevel level);

}
