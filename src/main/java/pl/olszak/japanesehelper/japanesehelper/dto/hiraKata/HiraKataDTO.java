package pl.olszak.japanesehelper.japanesehelper.dto.hiraKata;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class HiraKataDTO extends AbstractDTO{

    private Long id;

    private String sign;

    private String reading;
}
