package pl.olszak.japanesehelper.japanesehelper.domain.record;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class RecordEntity {

    private static final double FAILURE = 0.1d;

    private static final double SUCCESS = 0.05d;

    @Column(name = "weight", nullable = false)
    private double weight;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public void calculateWeight(boolean success){
        if(success){
            weight = weight - SUCCESS;
            if(weight < 0){
                weight = 0.00d;
            }
        } else {
            weight = weight + FAILURE;
            if(weight > 1){
                weight = 1.00d;
            }
        }
    }

    protected RecordEntity(){
        this.weight = 1.00d;
    }
}
