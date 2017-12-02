package pl.olszak.japanesehelper.japanesehelper.service.record.factory;

import org.springframework.beans.factory.annotation.Autowired;
import pl.olszak.japanesehelper.japanesehelper.dto.record.UserRecordDTO;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;

import java.util.List;

public class RecordFactoryImpl implements RecordFactory {

    @Autowired
    private List<RecordService> recordServices;

    @Override
    public RecordService getService(UserRecordDTO dto) throws Exception {

        for (RecordService service: recordServices){
            if(service.supports(dto.getType())){
                return service;
            }
        }
        throw new Exception("No service found");
    }
}
