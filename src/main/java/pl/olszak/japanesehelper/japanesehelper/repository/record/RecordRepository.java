package pl.olszak.japanesehelper.japanesehelper.repository.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.olszak.japanesehelper.japanesehelper.domain.record.RecordEntity;

import java.io.Serializable;

@Repository
public interface RecordRepository<ENTITY extends RecordEntity, ID extends Serializable> extends JpaRepository {
}
