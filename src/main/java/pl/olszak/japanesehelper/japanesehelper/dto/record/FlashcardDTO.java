package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

@Data
public class FlashcardDTO extends AbstractDTO {

    private Long id;
    private boolean success;
}
