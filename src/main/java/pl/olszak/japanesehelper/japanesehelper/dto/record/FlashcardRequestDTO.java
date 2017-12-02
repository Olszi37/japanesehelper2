package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;

@Data
public class FlashcardRequestDTO {

    private FlashcardType type;

    private JLPTLevel level;
}
