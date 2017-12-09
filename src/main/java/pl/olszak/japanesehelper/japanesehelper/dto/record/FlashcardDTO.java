package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = false)
public class FlashcardDTO extends AbstractDTO {

    private Long recordId;

}
