package pl.olszak.japanesehelper.japanesehelper.service.kanji;

import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.kanji.KanjiEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.kanji.KanjiDTO;
import pl.olszak.japanesehelper.japanesehelper.service.ServiceInterface;

import java.util.List;
import java.util.Optional;

public interface KanjiService extends ServiceInterface<KanjiEntity, KanjiDTO, Long>{

    List<KanjiDTO> findByLevel(JLPTLevel level);

    List<KanjiEntity> findByLevelEntities(JLPTLevel level);

    Optional<KanjiDTO> findBySign(String sign);

    List<KanjiEntity> getOther5Entities(Long id, JLPTLevel level);
}
