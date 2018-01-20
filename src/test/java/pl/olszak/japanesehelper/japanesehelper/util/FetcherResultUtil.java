package pl.olszak.japanesehelper.japanesehelper.util;

import pl.olszak.japanesehelper.japanesehelper.domain.hiragana.HiraganaRecordEntity;
import pl.olszak.japanesehelper.japanesehelper.domain.vocabulary.VocabularyRecordEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FetcherResultUtil {

    public static void getResultInfo(List<HiraganaRecordEntity> results) {
        Map<String, Long> info = results.stream().collect(Collectors
                .groupingBy(entity -> entity.getHiragana().getSign(), Collectors.counting()));

        for (String sign : info.keySet()) {
            System.out.println("For sign: " + sign + ", occurrences: " + info.get(sign) + " with weight: "
                    + getWeight(results, sign));
        }
    }

    public static double getWeight(List<HiraganaRecordEntity> results, String sign) {
        return results.stream().filter(entity -> sign.equals(entity.getHiragana().getSign()))
                .findFirst().get().getWeight();
    }

    public static void getGroupInfo(List<HiraganaRecordEntity> results) {
        Long group1 = results.stream().filter(entity ->
                entity.getWeight() >= 0.0d && entity.getWeight() < 0.4d).count();
        Long group2 = results.stream().filter(entity ->
                entity.getWeight() >= 0.4d && entity.getWeight() <= 0.7d).count();
        Long group3 = results.stream().filter(entity ->
                entity.getWeight() > 0.7d && entity.getWeight() <= 1.0d).count();

        System.out.println("For weight between 0.0 and 0.4: " + group1 + " occurrences");
        System.out.println("For weight between 0.4 and 0.7: " + group2 + " occurrences");
        System.out.println("For weight between 0.7 and 1.0: " + group3 + " occurrences");
    }

    public static void getResultInfoVocabulary(List<VocabularyRecordEntity> results) {
        Map<String, Long> info = results.stream().collect(Collectors
                .groupingBy(entity -> entity.getVocabulary().getWord(), Collectors.counting()));

        for (String sign : info.keySet()) {
            System.out.println("For sign: " + sign + ", occurrences: " + info.get(sign) + " with weight: "
                    + getWeightVocabulary(results, sign));
        }
    }

    public static double getWeightVocabulary(List<VocabularyRecordEntity> results, String sign) {
        return results.stream().filter(entity -> sign.equals(entity.getVocabulary().getWord()))
                .findFirst().get().getWeight();
    }

    public static void getGroupInfoVocabulary(List<VocabularyRecordEntity> results) {
        Long group1 = results.stream().filter(entity ->
                entity.getWeight() >= 0.0d && entity.getWeight() < 0.4d).count();
        Long group2 = results.stream().filter(entity ->
                entity.getWeight() >= 0.4d && entity.getWeight() <= 0.7d).count();
        Long group3 = results.stream().filter(entity ->
                entity.getWeight() > 0.7d && entity.getWeight() <= 1.0d).count();

        System.out.println("For weight between 0.0 and 0.4: " + group1 + " occurrences");
        System.out.println("For weight between 0.4 and 0.7: " + group2 + " occurrences");
        System.out.println("For weight between 0.7 and 1.0: " + group3 + " occurrences");
    }
}
