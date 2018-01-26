package pl.olszak.japanesehelper.japanesehelper.domain.hiragana;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hiragana_records")
@EqualsAndHashCode(callSuper = true)
public class HiraganaRecordEntity extends RecordEntity implements EntityInterface {

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "hiragana_record_id_gen",
            allocationSize = 1,
            sequenceName = "hiragana_record_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hiragana_record_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hiragana_id")
    private HiraganaEntity hiragana;
}
