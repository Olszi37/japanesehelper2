package pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.user.UserEntity;
import pl.olszak.japanesehelper.japanesehelper.dto.record.FlashcardType;
import pl.olszak.japanesehelper.japanesehelper.repository.record.HiraganaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.Fetcher;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;

import java.util.List;

@Component
public class HiraganaFetcher extends Fetcher<HiraganaRecordEntity>
        implements FlashcardFetcher<HiraganaRecordEntity> {

    private HiraganaRecordRepository hiraganaRecordRepository;

    @Autowired
    public HiraganaFetcher(HiraganaRecordRepository hiraganaRecordRepository) {
        this.hiraganaRecordRepository = hiraganaRecordRepository;
    }

    @Override
    public List<HiraganaRecordEntity> getGroup1(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.getRecordsBetweenGroup1(userEntity);
    }

    @Override
    public List<HiraganaRecordEntity> getGroup2(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.getRecordsBetweenGroup2(userEntity);
    }

    @Override
    public List<HiraganaRecordEntity> getGroup3(JLPTLevel level, UserEntity userEntity) {
        return hiraganaRecordRepository.getRecordsBetweenGroup3(userEntity);
    }

    @Override
    public boolean supports(FlashcardType type) {
        return FlashcardType.HIRAGANA.equals(type);
    }

//    @Override
//    public List<HiraganaRecordEntity> getFlashcards(JLPTLevel level) {
//        List<HiraganaRecordEntity> selected = Lists.newArrayList();
//
//        List<HiraganaRecordEntity> group1 = hiraganaRecordRepository.getRecordsBetweenGroup1();
//        List<HiraganaRecordEntity> group2 = hiraganaRecordRepository.getRecordsBetweenGroup2();
//        List<HiraganaRecordEntity> group3 = hiraganaRecordRepository.getRecordsBetweenGroup3();
//        List<List<HiraganaRecordEntity>> groups = getListOfLists(group1, group2, group3);
//
//        Random random = new Random();
//
//        for(int i = 0; i < 20; i++) {
//            int x = random.nextInt(groups.size());
//            List<HiraganaRecordEntity> takenList = groups.get(x);
//            selected.add(takenList.get(random.nextInt(takenList.size())));
//        }

// SOLUTION 2
//        double totalWeight = hiraganaRecordRepository.getSumOfWeights();
//        List<HiraganaRecordEntity> entities = hiraganaRecordRepository.findAll();
//
//        for(int i = 0; i < 20; i++) {
//            double random = Math.random() * totalWeight;
//            int randomIndex = -1;
//
//            for (int j = 0; j < entities.size(); ++j) {
//                random = random - entities.get(j).getWeight();
//                if (random <= 0.0d) {
//                    randomIndex = j;
//                    break;
//                }
//            }
//            selected.add(entities.get(randomIndex));
//        }

// SOLUTION 3
//        Random random = new Random();
//
//        double bestValue = 1.0d;
//
//        List<HiraganaRecordEntity> entities = hiraganaRecordRepository.findAll();
//
//        for(int i = 0; i < 20; i++) {
//            HiraganaRecordEntity result = new HiraganaRecordEntity();
//
//            for(HiraganaRecordEntity entity : entities){
//                double entityValue = entity.getWeight().compareTo(BigDecimal.ZERO) == 0 ? 0.01d : entity.getWeight().doubleValue();
//                double value = -Math.log(random.nextDouble()) / entityValue;
//
//                if(value < bestValue) {
//                    bestValue = value;
//                    result = entity;
//                }
//            }
//            selected.add(result);
//        }

//        return selected;
//    }
//
//    private List<List<HiraganaRecordEntity>> getListOfLists(List<HiraganaRecordEntity> group1,
//                                                            List<HiraganaRecordEntity> group2,
//                                                            List<HiraganaRecordEntity> group3) {
//        if (group1.isEmpty() && group2.isEmpty()) {
//            return Lists.newArrayList(group3, group3, group3, group3, group3, group3);
//        } else if (group1.isEmpty() && group3.isEmpty()) {
//            return Lists.newArrayList(group2, group2, group2, group2, group2, group2);
//        } else if (group2.isEmpty() && group3.isEmpty()) {
//            return Lists.newArrayList(group1, group1, group1, group1, group1, group1);
//        } else if(group1.isEmpty()) {
//            return Lists.newArrayList(group2, group2, group2, group2, group3, group3);
//        } else if (group2.isEmpty()) {
//            return Lists.newArrayList(group1, group1, group1, group1, group3, group3);
//        } else if (group3.isEmpty()) {
//            return Lists.newArrayList(group1, group1, group1, group1, group2, group2);
//        } else {
//            return Lists.newArrayList(group3, group2, group2, group1, group1, group1);
//        }
//    }
}
