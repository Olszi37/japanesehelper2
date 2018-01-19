package pl.olszak.japanesehelper.japanesehelper.service.fetcher.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.repository.record.HiraganaRecordRepository;
import pl.olszak.japanesehelper.japanesehelper.service.fetcher.FlashcardFetcher;

import java.util.List;
import java.util.Random;

@Component
public class HiraganaFetcher implements FlashcardFetcher<HiraganaRecordEntity> {

    private HiraganaRecordRepository hiraganaRecordRepository;

    @Autowired
    public HiraganaFetcher(HiraganaRecordRepository hiraganaRecordRepository) {
        this.hiraganaRecordRepository = hiraganaRecordRepository;
    }

    @Override
    public List<HiraganaRecordEntity> getFlashcards() {
        List<HiraganaRecordEntity> selected = Lists.newArrayList();

        List<HiraganaRecordEntity> group1 = hiraganaRecordRepository.getRecordsBetweenGroup1();
        List<HiraganaRecordEntity> group2 = hiraganaRecordRepository.getRecordsBetweenGroup2();
        List<HiraganaRecordEntity> group3 = hiraganaRecordRepository.getRecordsBetweenGroup3();
        List<List<HiraganaRecordEntity>> groups = Lists.newArrayList(group3, group2, group2, group1, group1, group1);

        //TODO zabezpieczenie przed pustymi listami

        Random random = new Random();

        for(int i = 0; i < 20; i++) {
            int x = random.nextInt(groups.size());
            System.out.println("Generated index: " + x);
            List<HiraganaRecordEntity> takenList = groups.get(x);
            selected.add(takenList.get(random.nextInt(takenList.size())));
        }

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

        return selected;
    }
}
