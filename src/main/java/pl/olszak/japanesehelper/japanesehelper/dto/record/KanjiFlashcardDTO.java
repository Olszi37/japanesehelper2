package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.dto.kanji.KanjiDTO;

import java.util.List;

@Data
public class KanjiFlashcardDTO extends FlashcardDTO{

    private KanjiDTO correct;

    private List<KanjiDTO> other;
}
