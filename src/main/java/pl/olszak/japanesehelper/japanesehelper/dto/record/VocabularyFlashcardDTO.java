package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.dto.vocabulary.VocabularyDTO;

import java.util.List;

@Data
public class VocabularyFlashcardDTO extends FlashcardDTO{

    private VocabularyDTO correct;

    private List<VocabularyDTO> other;
}
