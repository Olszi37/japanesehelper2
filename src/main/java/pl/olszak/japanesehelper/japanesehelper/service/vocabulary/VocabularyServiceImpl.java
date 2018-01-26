package pl.olszak.japanesehelper.japanesehelper.service.vocabulary;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.vocabulary.VocabularyConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.vocabulary.VocabularyDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.vocabulary.VocabularyRepository;
import pl.olszak.japanesehelper.japanesehelper.service.AbstractService;

import java.util.List;

@Service
@Transactional
public class VocabularyServiceImpl extends AbstractService<VocabularyEntity, VocabularyDTO, Long> implements VocabularyService {

    private VocabularyRepository repository;

    private VocabularyConverter converter;

    public VocabularyServiceImpl(VocabularyRepository repository, VocabularyConverter converter) {
        super(converter, repository);
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<VocabularyEntity> getOther5Entities(JLPTLevel level, Long id) {
        return repository.findTop5ByIdNotAndLevel(id, level);
    }

    @Override
    public List<VocabularyEntity> findAllEntitiesByLevel(JLPTLevel level) {
        return repository.findByLevel(level);
    }
}
