package pl.olszak.japanesehelper.japanesehelper.converter.hiragana;

import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.converter.Converter;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.hiraKata.HiraKataDTO;

@Component
public class HiraganaConverter implements Converter<HiraganaEntity, HiraKataDTO> {
    @Override
    public HiraganaEntity convertToEntity(HiraKataDTO dto) {
        return null;
    }

    @Override
    public HiraKataDTO convertToDTO(HiraganaEntity entity) {
        HiraKataDTO dto = new HiraKataDTO();
        dto.setId(entity.getId());
        dto.setSign(entity.getSign());
        dto.setReading(entity.getReading());
        return dto;
    }
    // TODO refactor
}
