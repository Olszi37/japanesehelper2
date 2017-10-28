package pl.olszak.japanesehelper.japanesehelper.domain.kanji;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "kanji_records")
public class KanjiRecordEntity extends RecordEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(
            name = "kanji_record_id_gen",
            allocationSize = 1,
            sequenceName = "kanji_record_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kanji_record_id_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kanji_id")
    private KanjiEntity kanji;
}
