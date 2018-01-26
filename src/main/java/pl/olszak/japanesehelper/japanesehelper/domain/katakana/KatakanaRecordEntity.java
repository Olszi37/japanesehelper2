package pl.olszak.japanesehelper.japanesehelper.domain.katakana;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "katakana_records")
@EqualsAndHashCode(callSuper = true)
public class KatakanaRecordEntity extends RecordEntity implements EntityInterface {

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "katakana_record_id_gen",
            allocationSize = 1,
            sequenceName = "katakana_record_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "katakana_record_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "katakana_id")
    private KatakanaEntity katakana;
}
