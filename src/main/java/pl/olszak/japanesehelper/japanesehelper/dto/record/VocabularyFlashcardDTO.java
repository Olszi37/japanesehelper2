package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.dto.vocabulary.VocabularyDTO;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class VocabularyFlashcardDTO extends FlashcardDTO{

    private VocabularyDTO correct;

    private List<VocabularyDTO> other;
}
