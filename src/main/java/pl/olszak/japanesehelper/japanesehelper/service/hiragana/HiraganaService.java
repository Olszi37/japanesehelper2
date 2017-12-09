package pl.olszak.japanesehelper.japanesehelper.service.hiragana;

import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kana.KanaDTO;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

import java.util.List;

public interface HiraganaService extends ServiceInterface<HiraganaEntity, KanaDTO, Long>{

    List<HiraganaEntity> getOther5Entities(Long id);
}
