package pl.olszak.japanesehelper.japanesehelper.service.hiragana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.hiragana.HiraganaConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kana.KanaDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.hiragana.HiraganaRepository;
import pl.olszak.japanesehelper.japanesehelper.service.AbstractService;

import java.util.List;

@Service
@Transactional
public class HiraganaServiceImpl extends AbstractService<HiraganaEntity, KanaDTO, Long> implements HiraganaService{

    private HiraganaRepository repository;
    private HiraganaConverter converter;

    @Autowired
    public HiraganaServiceImpl(HiraganaRepository repository, HiraganaConverter converter) {
        super(converter, repository);
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<HiraganaEntity> getOther5Entities(Long id) {
        return repository.findTop5ByIdNot(id);
    }
}
