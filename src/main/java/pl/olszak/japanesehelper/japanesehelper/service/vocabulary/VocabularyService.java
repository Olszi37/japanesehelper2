package pl.olszak.japanesehelper.japanesehelper.service.vocabulary;

import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.vocabulary.VocabularyDTO;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

import java.util.List;

public interface VocabularyService extends ServiceInterface<VocabularyEntity, VocabularyDTO, Long> {

    List<VocabularyEntity> getOther5Entities(JLPTLevel level, Long id);

    List<VocabularyEntity> findAllEntitiesByLevel(JLPTLevel level);
}
