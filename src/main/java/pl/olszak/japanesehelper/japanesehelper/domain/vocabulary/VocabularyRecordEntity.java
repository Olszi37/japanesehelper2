package pl.olszak.japanesehelper.japanesehelper.domain.vocabulary;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vocabulary_records")
public class VocabularyRecordEntity extends RecordEntity{

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
