package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.dto.kanji.KanjiDTO;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class KanjiFlashcardDTO extends FlashcardDTO{

    private KanjiDTO correct;

    private List<KanjiDTO> other;
}
