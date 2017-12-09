package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRecordDTO extends AbstractDTO{

    private Long recordId;
    private boolean correct;
}
