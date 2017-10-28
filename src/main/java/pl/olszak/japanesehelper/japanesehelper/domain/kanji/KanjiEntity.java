package pl.olszak.japanesehelper.japanesehelper.domain.kanji;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;

import javax.persistence.*;

@Data
@Entity
@Table(name = "kanji")
public class KanjiEntity implements EntityInterface{

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "sign", nullable = false)
    private String sign;

    @Column(name = "onyomi")
    private String onyomi;

    @Column(name = "kunyomi")
    private String kunyomi;

    @Column(name = "meaning", nullable = false)
    private String meaning;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private JLPTLevel level;
}
