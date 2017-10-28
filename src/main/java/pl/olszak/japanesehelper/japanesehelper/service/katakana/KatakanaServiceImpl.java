package pl.olszak.japanesehelper.japanesehelper.service.katakana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.katakana.KatakanaConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.hiraKata.HiraKataDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.katakana.KatakanaRepository;
import pl.olszak.japanesehelper.japanesehelper.service.AbstractService;

@Service
@Transactional
public class KatakanaServiceImpl extends AbstractService<KatakanaEntity, HiraKataDTO, Long> implements KatakanaService{

    private KatakanaRepository repository;

    private KatakanaConverter converter;

    @Autowired
    public KatakanaServiceImpl(KatakanaRepository repository, KatakanaConverter converter) {
        super(converter, repository);
        this.repository = repository;
        this.converter = converter;
    }
}
