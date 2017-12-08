package pl.olszak.japanesehelper.japanesehelper.converter.katakana;

import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.converter.Converter;
import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kana.KanaDTO;

@Component
public class KatakanaConverter implements Converter<KatakanaEntity, KanaDTO> {
    @Override
    public KatakanaEntity convertToEntity(KanaDTO dto) {
        return null;
    }

    @Override
    public KanaDTO convertToDTO(KatakanaEntity entity) {
        KanaDTO dto = new KanaDTO();
        dto.setId(entity.getId());
        dto.setSign(entity.getSign());
        dto.setReading(entity.getReading());
        return dto;
    }
}
