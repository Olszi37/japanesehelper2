package pl.olszak.japanesehelper.japanesehelper.service.record.factory;

import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;

public interface RecordFactory {

    RecordService getService(UserRecordDTO dto) throws Exception;
}
