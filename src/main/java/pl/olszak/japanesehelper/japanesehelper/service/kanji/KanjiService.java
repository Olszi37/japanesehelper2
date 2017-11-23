package pl.olszak.japanesehelper.japanesehelper.service.kanji;

import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kanji.KanjiDTO;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

public interface KanjiService extends ServiceInterface<KanjiEntity, KanjiDTO, Long>{

    List<KanjiDTO> findByLevel(JLPTLevel level);

    Optional<KanjiDTO> findBySign(String sign);
}
