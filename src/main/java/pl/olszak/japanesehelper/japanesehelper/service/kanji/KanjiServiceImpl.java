package pl.olszak.japanesehelper.japanesehelper.service.kanji;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.kanji.KanjiConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kanji.KanjiDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.kanji.KanjiRepository;
import pl.olszak.japanesehelper.japanesehelper.service.AbstractService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class KanjiServiceImpl extends AbstractService<KanjiEntity, KanjiDTO, Long> implements KanjiService{

    private KanjiConverter converter;
    private KanjiRepository repository;

    @Autowired
    public KanjiServiceImpl(KanjiConverter converter, KanjiRepository repository) {
        super(converter, repository);
        this.converter = converter;
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<KanjiDTO> findByLevel(JLPTLevel level) {
        return repository.findByLevel(level).stream().map(converter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<KanjiEntity> findByLevelEntities(JLPTLevel level) {
        return repository.findByLevel(level);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<KanjiDTO> findBySign(String sign) {
        return repository.findBySign(sign).map(converter::convertToDTO);
    }

    @Override
    public List<KanjiEntity> getOther5Entities(Long id, JLPTLevel level) {
        return repository.findTop5ByIdNotAndLevel(id, level);
    }

}
