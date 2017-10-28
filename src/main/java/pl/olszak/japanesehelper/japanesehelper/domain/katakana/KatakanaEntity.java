package pl.olszak.japanesehelper.japanesehelper.domain.katakana;

import lombok.Data;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "katakana")
public class KatakanaEntity implements EntityInterface {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "sign", nullable = false)
    private String sign;

    @Column(name = "reading", nullable = false)
    private String reading;
}
