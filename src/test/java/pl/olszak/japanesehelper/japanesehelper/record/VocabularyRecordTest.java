package pl.olszak.japanesehelper.japanesehelper.record;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.olszak.japanesehelper.japanesehelper.JapanesehelperApplication;
import pl.olszak.japanesehelper.japanesehelper.converter.user.UserConverter;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.repository.record.VocabularyRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.repository.user.UserRepository;
import pl.olszak.japanesehelper.japanesehelper.repository.vocabulary.VocabularyRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.factory.FetcherFactory;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl.VocabularyFetcher;
import pl.olszak.japanesehelper.japanesehelper.service.user.UserService;
import pl.olszak.japanesehelper.japanesehelper.util.FetcherResultUtil;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JapanesehelperApplication.class)
public class VocabularyRecordTest {

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private VocabularyRecordRepository vocabularyRecordRepository;

    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private FetcherFactory fetcherFactory;

    private MockMvc restHiraganaRecordMock;

    public List<VocabularyRecordEntity> getRecordSetWithEmptyWeights() {
        return vocabularyRecordRepository.findAll();
    }

    private void saveEmptyRecords(UserEntity user){
        vocabularyRecordRepository.deleteAll();
        List<VocabularyEntity> vocabularies = vocabularyRepository.findByLevel(JLPTLevel.N5);

        List<VocabularyRecordEntity> recordEntities = Lists.newArrayList();

        vocabularies.forEach(vocabulary -> {
            VocabularyRecordEntity entity = new VocabularyRecordEntity();
            entity.setUser(user);
            entity.setVocabulary(vocabulary);
            recordEntities.add(entity);
        });

        vocabularyRecordRepository.save(recordEntities);
    }

    public void saveRecordSetOne() throws Exception {
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<VocabularyRecordEntity> setOne = vocabularyRecordRepository.findAll();

        Random random = new Random();
        setOne = setOne.stream().peek(entity -> entity.setWeight(random.nextInt(10) * 0.1))
                .collect(Collectors.toList());
        vocabularyRecordRepository.save(setOne);
    }

    public void saveRecordSetTwo() throws Exception {
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<VocabularyRecordEntity> setOne = vocabularyRecordRepository.findAll();
        System.out.println(setOne);

        setOne = setOne.stream().peek(entity -> entity.setWeight(0.0d)).collect(Collectors.toList());
        vocabularyRecordRepository.save(setOne);
    }

    public void saveRecordSetThree() throws Exception {
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<VocabularyRecordEntity> setOne = vocabularyRecordRepository.findAll();

        Random random = new Random();
        setOne = setOne.stream().peek(entity -> entity.setWeight(random.nextInt(5) * 0.1))
                .collect(Collectors.toList());
        vocabularyRecordRepository.save(setOne);
    }

    public void saveRecordSetFour() throws Exception {
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<VocabularyRecordEntity> setOne = vocabularyRecordRepository.findAll();

        Random random = new Random();
        setOne = setOne.stream().peek(entity -> entity.setWeight((random.nextInt(4) + 6) * 0.1))
                .collect(Collectors.toList());
        vocabularyRecordRepository.save(setOne);
    }

    public void saveRecordSetFive() throws Exception {
        UserEntity userEntity = getUser();

        saveEmptyRecords(userEntity);

        List<VocabularyRecordEntity> setOne = vocabularyRecordRepository.findAll();
        System.out.println(setOne);

        setOne = setOne.stream().peek(entity -> entity.setWeight(1.0d)).collect(Collectors.toList());
        setOne.get(2).setWeight(0.2d);
        setOne.get(8).setWeight(0.1d);
        setOne.get(25).setWeight(0.35d);
        setOne.get(36).setWeight(0.15d);
        vocabularyRecordRepository.save(setOne);
    }

    @Test
    public void fetchFlashcardsTest1() throws Exception {
        UserEntity user = getUser();
        saveRecordSetOne();
        VocabularyFetcher fetcher = (VocabularyFetcher) getVocabularyFetcher();

        List<VocabularyRecordEntity> fetched = fetcher.getFlashcards(JLPTLevel.N5, user);
        FetcherResultUtil.getResultInfoVocabulary(fetched);
        FetcherResultUtil.getGroupInfoVocabulary(fetched);
    }

    @Test
    public void fetchFlashcardsTest2() throws Exception {
        UserEntity user = getUser();
        saveRecordSetTwo();
        VocabularyFetcher fetcher = (VocabularyFetcher) getVocabularyFetcher();

        List<VocabularyRecordEntity> fetched = fetcher.getFlashcards(JLPTLevel.N5, user);
        FetcherResultUtil.getResultInfoVocabulary(fetched);
        FetcherResultUtil.getGroupInfoVocabulary(fetched);
    }

    @Test
    public void fetchFlashcardsTest3() throws Exception {
        UserEntity user = getUser();
        saveRecordSetThree();
        VocabularyFetcher fetcher = (VocabularyFetcher) getVocabularyFetcher();

        List<VocabularyRecordEntity> fetched = fetcher.getFlashcards(JLPTLevel.N5, user);
        FetcherResultUtil.getResultInfoVocabulary(fetched);
        FetcherResultUtil.getGroupInfoVocabulary(fetched);
    }

    @Test
    public void fetchFlashcardsTest4() throws Exception {
        UserEntity user = getUser();
        saveRecordSetFour();
        VocabularyFetcher fetcher = (VocabularyFetcher) getVocabularyFetcher();

        List<VocabularyRecordEntity> fetched = fetcher.getFlashcards(JLPTLevel.N5, user);
        FetcherResultUtil.getResultInfoVocabulary(fetched);
        FetcherResultUtil.getGroupInfoVocabulary(fetched);
    }

    @Test
    public void fetchFlashcardsTest5() throws Exception {
        UserEntity user = getUser();
        saveRecordSetFive();
        VocabularyFetcher fetcher = (VocabularyFetcher) getVocabularyFetcher();

        List<VocabularyRecordEntity> fetched = fetcher.getFlashcards(JLPTLevel.N5, user);
        FetcherResultUtil.getResultInfoVocabulary(fetched);
        FetcherResultUtil.getGroupInfoVocabulary(fetched);
    }

    private FlashcardFetcher getVocabularyFetcher() {
        return fetcherFactory.getFetcher(FlashcardType.VOCABULARY);
    }

    private UserEntity getUser() throws Exception {
        return userRepository.findOneByLogin("user").orElseThrow(() -> new Exception(""));
    }
}
