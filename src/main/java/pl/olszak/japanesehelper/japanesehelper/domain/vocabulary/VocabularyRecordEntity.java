package pl.olszak.japanesehelper.japanesehelper.domain.vocabulary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vocabulary_records")
@EqualsAndHashCode(callSuper = true)
public class VocabularyRecordEntity extends RecordEntity implements EntityInterface {

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "vocabulary_record_id_gen",
            allocationSize = 1,
            sequenceName = "vocabulary_record_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vocabulary_record_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vocabulary_id")
    private VocabularyEntity vocabulary;
}
