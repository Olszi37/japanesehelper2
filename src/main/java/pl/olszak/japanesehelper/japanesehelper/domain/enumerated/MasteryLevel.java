package pl.olszak.japanesehelper.japanesehelper.domain.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
public enum MasteryLevel {

    UKNOWN(new BigDecimal("0"), new BigDecimal("0.1")),
    WEAK(new BigDecimal("0.1"), new BigDecimal("0.4")),
    INTERMEDIATE(new BigDecimal("0.4"), new BigDecimal("0.7")),
    WELL_KNOWN(new BigDecimal("0.7"), new BigDecimal("0.9")),
    MASTERED(new BigDecimal("0.9"), new BigDecimal("1"));

    @Getter
    private BigDecimal lowBorder;

    @Getter
    private BigDecimal topBorder;
}
