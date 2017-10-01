package pl.olszak.japanesehelper.japanesehelper.converter.katakana;

import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.converter.Converter;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.hiraKata.HiraKataDTO;

@Component
public class KatakanaConverter implements Converter<KatakanaEntity, HiraKataDTO> {
    @Override
    public KatakanaEntity convertToEntity(HiraKataDTO dto) {
        return null;
    }

    @Override
    public HiraKataDTO convertToDTO(KatakanaEntity entity) {
        HiraKataDTO dto = new HiraKataDTO();
        dto.setId(entity.getId());
        dto.setSign(entity.getSign());
        dto.setReading(entity.getReading());
        return dto;
    }
}
