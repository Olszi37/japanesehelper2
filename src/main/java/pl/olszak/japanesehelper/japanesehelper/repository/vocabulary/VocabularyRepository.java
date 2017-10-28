package pl.olszak.japanesehelper.japanesehelper.repository.vocabulary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyEntity;

@Repository
public interface VocabularyRepository extends JpaRepository<VocabularyEntity, Long>{
}
