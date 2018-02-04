package pl.olszak.japanesehelper.japanesehelper.dto.record;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

@Data
public class StatsDTO extends AbstractDTO {

    Long untouched;
    Long weakKnown;
    Long midKnown;
    Long wellKnown;
    Long mastered;

}
