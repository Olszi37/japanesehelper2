package pl.olszak.japanesehelper.japanesehelper.record;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.olszak.japanesehelper.japanesehelper.JapanesehelperApplication;
import pl.olszak.japanesehelper.japanesehelper.controller.hiragana.HiraganaRecordController;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JapanesehelperApplication.class)
public class HiraganaRecordTests {

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc restHiraganaRecordMock;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        HiraganaRecordController hiraganaRecordController = new HiraganaRecordController();
        this.restHiraganaRecordMock = MockMvcBuilders.standaloneSetup(hiraganaRecordController)
                .setMessageConverters(jacksonMessageConverter).build();
    }

    public List<HiraganaRecordEntity> getRecordSetOne(){
        List<HiraganaRecordEntity> records = Lists.newArrayList();
        //TODO

        return records;
    }
}
