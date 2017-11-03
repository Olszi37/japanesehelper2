package pl.olszak.japanesehelper.japanesehelper.domain.record;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@Data
@MappedSuperclass
public class RecordEntity implements EntityInterface{

    private static final BigDecimal FAILURE = new BigDecimal("0.1");

    private static final BigDecimal SUCCESS = new BigDecimal("0.05");

    @Column(name = "weight", nullable = false)
    @Setter(AccessLevel.NONE)
    private BigDecimal weight;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void calculateWeight(boolean success){
        if(success){
            weight = weight.add(SUCCESS);
            if(weight.doubleValue() > 1){
                weight = new BigDecimal("1.00");
            }
        } else {
            weight = weight.subtract(FAILURE);
            if(weight.doubleValue() < 0){
                weight = new BigDecimal("0.00");
            }
        }
    }

    protected RecordEntity(){
        this.weight = new BigDecimal("0.00");
    }
}
