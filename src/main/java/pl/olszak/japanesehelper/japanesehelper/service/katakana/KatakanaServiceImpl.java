package pl.olszak.japanesehelper.japanesehelper.service.katakana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.katakana.KatakanaConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kana.KanaDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.katakana.KatakanaRepository;
import pl.olszak.japanesehelper.japanesehelper.service.AbstractService;

import java.util.List;

@Service
@Transactional
public class KatakanaServiceImpl extends AbstractService<KatakanaEntity, KanaDTO, Long> implements KatakanaService{

    private KatakanaRepository repository;

    private KatakanaConverter converter;

    @Autowired
    public KatakanaServiceImpl(KatakanaRepository repository, KatakanaConverter converter) {
        super(converter, repository);
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<KatakanaEntity> getOther5Entities(Long id) {
        return repository.findTop5ByIdNot(id);
    }
}
