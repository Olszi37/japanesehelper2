package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class FlashcardRequestDTO extends AbstractDTO {

    private JLPTLevel level;

    private int flashcardQuantity;
}
