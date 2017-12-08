package pl.olszak.japanesehelper.japanesehelper.dto.vocabulary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class VocabularyDTO extends AbstractDTO{

    private Long id;

    private String word;

    private String furigana;

    private String meaning;

}
