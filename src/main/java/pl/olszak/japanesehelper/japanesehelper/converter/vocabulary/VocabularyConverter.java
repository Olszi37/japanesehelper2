package pl.olszak.japanesehelper.japanesehelper.converter.vocabulary;

import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.converter.Converter;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.vocabulary.VocabularyDTO;

@Component
public class VocabularyConverter implements Converter<VocabularyEntity, VocabularyDTO> {

    @Override
    public VocabularyEntity convertToEntity(VocabularyDTO dto) {
        return null;
    }

    @Override
    public VocabularyDTO convertToDTO(VocabularyEntity entity) {
        VocabularyDTO dto = new VocabularyDTO();
        dto.setId(entity.getId());
        dto.setWord(entity.getWord());
        dto.setFurigana(entity.getFurigana());
        dto.setMeaning(entity.getMeaning());
        return dto;
    }
}
