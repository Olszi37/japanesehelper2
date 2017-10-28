package pl.olszak.japanesehelper.japanesehelper.service.kanji;

import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.dto.kanji.KanjiDTO;

import java.util.List;
import java.util.Optional;

public interface KanjiService {

    List<KanjiDTO> findByLevel(JLPTLevel level);

    Optional<KanjiDTO> findBySign(String sign);
}
