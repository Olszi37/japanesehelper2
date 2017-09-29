package pl.olszak.japanesehelper.japanesehelper.domain.hiragana;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hiragana")
public class HiraganaEntity implements EntityInterface {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hiragana_seq")
    @SequenceGenerator(name = "hiragana_seq", sequenceName = "hiragana_id_gen")
    private Long id;

    @Column(name = "sign")
    private String sign;

    @Column(name = "reading")
    private String reading;
}
