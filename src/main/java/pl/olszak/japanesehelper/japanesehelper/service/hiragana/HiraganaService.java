package pl.olszak.japanesehelper.japanesehelper.service.hiragana;

import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.hiraKata.HiraKataDTO;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

public interface HiraganaService extends ServiceInterface<HiraganaEntity, HiraKataDTO, Long>{

    HiraganaEntity findOneEntity(Long id);
}
