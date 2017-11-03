package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

import java.util.List;

@Data
public class UserRecordDTO extends AbstractDTO{

    private FlashcardType type;

    private List<FlashcardDTO> flashcards;
}
