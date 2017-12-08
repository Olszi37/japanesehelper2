package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

import java.util.List;

@Data
public class NewFlashcardDTO extends AbstractDTO{

    private List<Object> types;

    private Object answer;

}
