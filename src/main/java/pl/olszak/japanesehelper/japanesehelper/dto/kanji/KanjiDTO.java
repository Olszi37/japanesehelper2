package pl.olszak.japanesehelper.japanesehelper.dto.kanji;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class KanjiDTO extends AbstractDTO{

    private Long id;
    
    private String sign;

    private String onyomi;

    private String kunyomi;

    private String meaning;
}
