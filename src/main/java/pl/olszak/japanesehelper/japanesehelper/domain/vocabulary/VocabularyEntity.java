package pl.olszak.japanesehelper.japanesehelper.domain.vocabulary;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vocabulary")
public class VocabularyEntity implements EntityInterface{

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "word", nullable = false)
    private String word;

    @Column(name = "furigana")
    private String furigana;

    @Column(name = "meaning", nullable = false)
    private String meaning;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private JLPTLevel level;
}
