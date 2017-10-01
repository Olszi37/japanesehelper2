package pl.olszak.japanesehelper.japanesehelper.service.katakana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.katakana.KatakanaConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.hiraKata.HiraKataDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.katakana.KatakanaRepository;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class KatakanaService implements ServiceInterface<HiraKataDTO>{

    private KatakanaRepository repository;

    private KatakanaConverter converter;

    @Autowired
    public KatakanaService(KatakanaRepository repository, KatakanaConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<HiraKataDTO> findAll() {
        return repository.findAll().stream().map(converter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HiraKataDTO> findById(Long id) {
        Optional<KatakanaEntity> entity = Optional.of(repository.findOne(id));
        return entity.map(converter::convertToDTO);
    }

    @Override
    public HiraKataDTO save(HiraKataDTO dto) {
        //do nothing
        return null;
    }

    @Override
    public void delete(HiraKataDTO dto) {
        //do nothing
    }
}
