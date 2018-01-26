package pl.olszak.japanesehelper.japanesehelper.converter.kanji;

import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.converter.Converter;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kanji.KanjiDTO;

@Component
public class KanjiConverter implements Converter<KanjiEntity, KanjiDTO>{

    @Override
    public KanjiEntity convertToEntity(KanjiDTO dto) {
        return null;
    }

    @Override
    public KanjiDTO convertToDTO(KanjiEntity entity) {
        KanjiDTO dto = new KanjiDTO();
        dto.setId(entity.getId());
        dto.setSign(entity.getSign());
        dto.setOnyomi(entity.getOnyomi());
        dto.setKunyomi(entity.getKunyomi());
        dto.setMeaning(entity.getMeaning());
        return dto;
    }
}
