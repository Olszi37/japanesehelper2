package pl.olszak.japanesehelper.japanesehelper.record;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
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
import pl.olszak.japanesehelper.japanesehelper.converter.user.UserConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.repository.record.HiraganaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.repository.user.UserRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl.HiraganaFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.hiragana.HiraganaService;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JapanesehelperApplication.class)
public class HiraganaRecordTests {

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private HiraganaRecordRepository hiraganaRecordRepository;

    @Autowired
    private HiraganaService hiraganaService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    private MockMvc restHiraganaRecordMock;

    private double[] weights1 = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.2, 0.15, 0.1,
                                 0.0, 0.45, 0.45, 0.25, 0.1, 0.7, 0.0, 0.2, 0.4, 0.9,
                                 0.5, 0.55, 0.6, 0.25, 0.25, 0.2, 0.75, 0.8, 0.45, 0.4,
                                 0.4, 0.95, 0.9, 0.8, 1.0, 0.6, 0.75, 0.75, 0.8, 0.9,
                                 0.75, 0.65, 0.2, 0.3, 0.35, 0.25 };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HiraganaRecordController hiraganaRecordController = new HiraganaRecordController();
        this.restHiraganaRecordMock = MockMvcBuilders.standaloneSetup(hiraganaRecordController)
                .setMessageConverters(jacksonMessageConverter).build();
    }

    public List<HiraganaRecordEntity> getRecordSetWithEmptyWeights() {
        return hiraganaRecordRepository.findAll();
    }

    private void saveEmptyRecords(UserEntity user){
        List<HiraganaEntity> hiraganas = hiraganaService.findAllEntities();
        System.out.println("hiraganas: " + hiraganas);

        List<HiraganaRecordEntity> recordEntities = Lists.newArrayList();

        hiraganas.forEach(hiragana -> {
            HiraganaRecordEntity entity = new HiraganaRecordEntity();
            entity.setUser(user);
            entity.setHiragana(hiragana);
            recordEntities.add(entity);
        });

        hiraganaRecordRepository.save(recordEntities);
    }

    public void saveRecordSetOne() throws Exception {
        UserEntity userEntity = userRepository.findOneByLogin("user").orElseThrow(() -> new Exception(""));

        saveEmptyRecords(userEntity);

        List<HiraganaRecordEntity> setOne = hiraganaRecordRepository.findAll();
        System.out.println(setOne);

        AtomicInteger i = new AtomicInteger(0);
        setOne = setOne.stream().peek(entity -> entity.setWeight(weights1[i.getAndIncrement()]))
                .collect(Collectors.toList());
        hiraganaRecordRepository.save(setOne);
    }

    public void saveRecordSetTwo() throws Exception {
        UserEntity userEntity = userRepository.findOneByLogin("user").orElseThrow(() -> new Exception(""));

        saveEmptyRecords(userEntity);

        List<HiraganaRecordEntity> setOne = hiraganaRecordRepository.findAll();
        System.out.println(setOne);

        AtomicInteger i = new AtomicInteger(0);
        setOne = setOne.stream().peek(entity -> entity.setWeight(0.0d)).collect(Collectors.toList());
        hiraganaRecordRepository.save(setOne);
    }

    public void saveRecordSetThree() throws Exception {
        UserEntity userEntity = userRepository.findOneByLogin("user").orElseThrow(() -> new Exception(""));

        saveEmptyRecords(userEntity);

        List<HiraganaRecordEntity> setOne = hiraganaRecordRepository.findAll();
        System.out.println(setOne);

        AtomicInteger i = new AtomicInteger(0);
        setOne = setOne.stream().peek(entity -> entity.setWeight(0.0d)).collect(Collectors.toList());
        setOne.get(0).setWeight(0.2d);
        hiraganaRecordRepository.save(setOne);
    }

    @Test
    public void fetchFlashcardsTest1() throws Exception {
        saveRecordSetOne();
        HiraganaFetcher fetcher = new HiraganaFetcher(hiraganaRecordRepository);

        List<HiraganaRecordEntity> fetched = fetcher.getFlashcards();
        fetched.forEach(record -> {
            System.out.println("w: " + record.getWeight() + " for: " + record.getHiragana().getId());
        });
    }

    @Test
    public void fetchFlashcardsTest2() throws Exception {
        saveRecordSetTwo();
        HiraganaFetcher fetcher = new HiraganaFetcher(hiraganaRecordRepository);

        List<HiraganaRecordEntity> fetched = fetcher.getFlashcards();
        fetched.forEach(record -> {
            System.out.println("w: " + record.getWeight() + " for: " + record.getHiragana().getId());
        });
    }

    @Test
    public void fetchFlashcardsTest3() throws Exception {
        saveRecordSetThree();
        HiraganaFetcher fetcher = new HiraganaFetcher(hiraganaRecordRepository);

        List<HiraganaRecordEntity> fetched = fetcher.getFlashcards();
        fetched.forEach(record -> {
            System.out.println("w: " + record.getWeight() + " for: " + record.getHiragana().getId());
        });
    }
}
