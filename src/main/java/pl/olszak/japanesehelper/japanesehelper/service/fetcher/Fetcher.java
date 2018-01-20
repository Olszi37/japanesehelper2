package pl.olszak.japanesehelper.japanesehelper.service.fetcher;

import com.google.common.collect.Lists;
import pl.olszak.japanesehelper.japanesehelper.domain.enumerated.JLPTLevel;
import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;

import java.util.List;
import java.util.Random;

public abstract class Fetcher<RECORD extends RecordEntity> {

    public List<RECORD> getFlashcards(JLPTLevel level) {
        List<RECORD> selected = Lists.newArrayList();

        List<RECORD> group1 = getGroup1(level);
        List<RECORD> group2 = getGroup2(level);
        List<RECORD> group3 = getGroup3(level);

        List<List<RECORD>> listOfGroups = getListOfGroups(group1, group2, group3);

        Random random = new Random();

        for(int i = 0; i < 20; i++) {
            int x = random.nextInt(listOfGroups.size());
            List<RECORD> takenList = listOfGroups.get(x);
            selected.add(takenList.get(random.nextInt(takenList.size())));
        }

        return selected;
    }

    public abstract List<RECORD> getGroup1(JLPTLevel level);
    public abstract List<RECORD> getGroup2(JLPTLevel level);
    public abstract List<RECORD> getGroup3(JLPTLevel level);

    private List<List<RECORD>> getListOfGroups(List<RECORD> group1,
                                              List<RECORD> group2,
                                              List<RECORD> group3) {
        if (group1.isEmpty() && group2.isEmpty()) {
            return Lists.newArrayList(group3, group3, group3, group3, group3, group3);
        } else if (group1.isEmpty() && group3.isEmpty()) {
            return Lists.newArrayList(group2, group2, group2, group2, group2, group2);
        } else if (group2.isEmpty() && group3.isEmpty()) {
            return Lists.newArrayList(group1, group1, group1, group1, group1, group1);
        } else if(group1.isEmpty()) {
            return Lists.newArrayList(group2, group2, group2, group2, group3, group3);
        } else if (group2.isEmpty()) {
            return Lists.newArrayList(group1, group1, group1, group1, group3, group3);
        } else if (group3.isEmpty()) {
            return Lists.newArrayList(group1, group1, group1, group1, group2, group2);
        } else {
            return Lists.newArrayList(group3, group2, group2, group1, group1, group1);
        }
    }
}
