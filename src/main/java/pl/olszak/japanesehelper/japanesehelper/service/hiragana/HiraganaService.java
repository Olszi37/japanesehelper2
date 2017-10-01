package pl.olszak.japanesehelper.japanesehelper.service.hiragana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.hiragana.HiraganaConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.hiraKata.HiraKataDTO;
import pl.olszak.japanesehelper.japanesehelper.repository.hiragana.HiraganaRepository;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class HiraganaService implements ServiceInterface<HiraKataDTO>{

    private HiraganaRepository repository;
    private HiraganaConverter converter;

    @Autowired
    public HiraganaService(HiraganaRepository repository, HiraganaConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<HiraKataDTO> findAll() {
        return repository.findAll().stream().map(converter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HiraKataDTO> findById(Long id) {
        Optional<HiraganaEntity> entity = Optional.of(repository.findOne(id));
        return entity.map(converter::convertToDTO);
    }

    @Override
    public HiraKataDTO save(HiraKataDTO dto) {
        // do nothing
        return null;
    }

    @Override
    public void delete(HiraKataDTO dto) {
        // do nothing
    }
}
