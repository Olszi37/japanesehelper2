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
import pl.olszak.japanesehelper.japanesehelper.util.FetcherResultUtil;

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

    private double[] weights2 = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1,
                                 0.0, 0.0, 0.0, 0.25, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                                 0.3, 0.2, 0.6, 0.2, 0.1, 0.2, 0.65, 0.3, 0.3, 0.4,
                                 0.4, 0.15, 0.6, 0.5, 0.55, 0.35, 0.5, 0.5, 0.6, 0.65,
                                 0.15, 0.65, 0.2, 0.3, 0.35, 0.25 };

    private double[] weights3 = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
                                 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
                                 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
                                 0.9, 0.95, 0.9, 0.65, 0.5, 0.6, 0.45, 0.4, 0.6, 0.55,
                                 0.7, 0.65, 0.65, 0.5, 0.45, 0.55 };

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
        hiraganaRecordRepository.deleteAll();
        List<HiraganaEntity> hiraganas = hiraganaService.findAllEntities();

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
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<HiraganaRecordEntity> setOne = hiraganaRecordRepository.findAll();

        AtomicInteger i = new AtomicInteger(0);
        setOne = setOne.stream().peek(entity -> entity.setWeight(weights1[i.getAndIncrement()]))
                .collect(Collectors.toList());
        hiraganaRecordRepository.save(setOne);
    }

    public void saveRecordSetTwo() throws Exception {
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<HiraganaRecordEntity> setOne = hiraganaRecordRepository.findAll();
        System.out.println(setOne);

        setOne = setOne.stream().peek(entity -> entity.setWeight(0.0d)).collect(Collectors.toList());
        hiraganaRecordRepository.save(setOne);
    }

    public void saveRecordSetThree() throws Exception {
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<HiraganaRecordEntity> setOne = hiraganaRecordRepository.findAll();

        AtomicInteger i = new AtomicInteger(0);
        setOne = setOne.stream().peek(entity -> entity.setWeight(weights2[i.getAndIncrement()]))
                .collect(Collectors.toList());
        hiraganaRecordRepository.save(setOne);
    }

    public void saveRecordSetFour() throws Exception {
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<HiraganaRecordEntity> setOne = hiraganaRecordRepository.findAll();

        AtomicInteger i = new AtomicInteger(0);
        setOne = setOne.stream().peek(entity -> entity.setWeight(weights3[i.getAndIncrement()]))
                .collect(Collectors.toList());
        hiraganaRecordRepository.save(setOne);
    }

    public void saveRecordSetFive() throws Exception {
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<HiraganaRecordEntity> setOne = hiraganaRecordRepository.findAll();
        System.out.println(setOne);

        setOne = setOne.stream().peek(entity -> entity.setWeight(1.0d)).collect(Collectors.toList());
        setOne.get(2).setWeight(0.2d);
        setOne.get(8).setWeight(0.1d);
        setOne.get(25).setWeight(0.35d);
        setOne.get(36).setWeight(0.15d);
        hiraganaRecordRepository.save(setOne);
    }

    @Test
    public void fetchFlashcardsTest1() throws Exception {
        UserEntity userEntity = getUser();
        saveRecordSetOne();
        HiraganaFetcher fetcher = new HiraganaFetcher(hiraganaRecordRepository);

        List<HiraganaRecordEntity> fetched = fetcher.getFlashcards(null, userEntity);
        FetcherResultUtil.getResultInfo(fetched);
        FetcherResultUtil.getGroupInfo(fetched);
    }

    @Test
    public void fetchFlashcardsTest2() throws Exception {
        UserEntity userEntity = getUser();
        saveRecordSetTwo();
        HiraganaFetcher fetcher = new HiraganaFetcher(hiraganaRecordRepository);

        List<HiraganaRecordEntity> fetched = fetcher.getFlashcards(null, userEntity);
        FetcherResultUtil.getResultInfo(fetched);
        FetcherResultUtil.getGroupInfo(fetched);
    }

    @Test
    public void fetchFlashcardsTest3() throws Exception {
        UserEntity userEntity = getUser();
        saveRecordSetThree();
        HiraganaFetcher fetcher = new HiraganaFetcher(hiraganaRecordRepository);

        List<HiraganaRecordEntity> fetched = fetcher.getFlashcards(null, userEntity);
        FetcherResultUtil.getResultInfo(fetched);
        FetcherResultUtil.getGroupInfo(fetched);
    }

    @Test
    public void fetchFlashcardsTest4() throws Exception {
        UserEntity userEntity = getUser();
        saveRecordSetFour();
        HiraganaFetcher fetcher = new HiraganaFetcher(hiraganaRecordRepository);

        List<HiraganaRecordEntity> fetched = fetcher.getFlashcards(null, userEntity);
        FetcherResultUtil.getResultInfo(fetched);
        FetcherResultUtil.getGroupInfo(fetched);
    }

    @Test
    public void fetchFlashcardsTest5() throws Exception {
        UserEntity userEntity = getUser();
        saveRecordSetFive();
        HiraganaFetcher fetcher = new HiraganaFetcher(hiraganaRecordRepository);

        List<HiraganaRecordEntity> fetched = fetcher.getFlashcards(null, userEntity);
        FetcherResultUtil.getResultInfo(fetched);
        FetcherResultUtil.getGroupInfo(fetched);
    }

    private UserEntity getUser() throws Exception {
        return userRepository.findOneByLogin("user").orElseThrow(() -> new Exception("User not found!"));
    }
}
