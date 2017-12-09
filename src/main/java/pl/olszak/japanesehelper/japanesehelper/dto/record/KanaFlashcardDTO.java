package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.dto.kana.KanaDTO;

import java.util.List;

@Data
public class KanaFlashcardDTO extends FlashcardDTO{

    private KanaDTO correct;

    private List<KanaDTO> other;
}
