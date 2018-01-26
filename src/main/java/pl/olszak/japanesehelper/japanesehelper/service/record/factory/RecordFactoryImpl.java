package pl.olszak.japanesehelper.japanesehelper.service.record.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.service.record.RecordService;

import java.util.List;

@Component
public class RecordFactoryImpl implements RecordFactory {

    @Autowired
    private List<RecordService> recordServices;

    @Override
    public RecordService getService(FlashcardType type) throws IllegalStateException {

        for (RecordService service: recordServices){
            if(service.supports(type)){
                return service;
            }
        }
        throw new IllegalStateException("No matching service found!");
    }
}
