package pl.olszak.japanesehelper.japanesehelper.domain.record;

import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
public class RecordEntity implements EntityInterface{

    private static final BigDecimal FAILURE = new BigDecimal("0.1");

    private static final BigDecimal SUCCESS = new BigDecimal("0.05");

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
