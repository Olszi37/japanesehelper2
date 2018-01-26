package pl.olszak.japanesehelper.japanesehelper.converter.hiragana;

import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.converter.Converter;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kana.KanaDTO;

@Component
public class HiraganaConverter implements Converter<HiraganaEntity, KanaDTO> {
    @Override
    public HiraganaEntity convertToEntity(KanaDTO dto) {
        return null;
    }

    @Override
    public KanaDTO convertToDTO(HiraganaEntity entity) {
        KanaDTO dto = new KanaDTO();
        dto.setId(entity.getId());
        dto.setSign(entity.getSign());
        dto.setReading(entity.getReading());
        return dto;
    }
}
