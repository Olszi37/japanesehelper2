package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyRecordEntity;

import java.util.Optional;

@Repository("vicabularyRecord")
public interface VocabularyRecordRepository extends JpaRepository<VocabularyRecordEntity, Long> {

    Optional<VocabularyRecordEntity> findOneByVocabularyIdAndUserId(Long vocabularyId, Long userId);
}
