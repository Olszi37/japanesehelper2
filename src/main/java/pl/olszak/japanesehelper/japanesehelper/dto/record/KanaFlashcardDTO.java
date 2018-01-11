package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.dto.kana.KanaDTO;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class KanaFlashcardDTO extends FlashcardDTO{

    private KanaDTO correct;

    private List<KanaDTO> other;
}
