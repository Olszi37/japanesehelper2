package pl.olszak.japanesehelper.japanesehelper.service.katakana;

import pl.olszak.japanesehelper.japanesehelper.domain.katakana.KatakanaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kana.KanaDTO;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

import java.util.List;

public interface KatakanaService extends ServiceInterface<KatakanaEntity, KanaDTO, Long>{

    List<KatakanaEntity> getOther5Entities(Long id);
}
