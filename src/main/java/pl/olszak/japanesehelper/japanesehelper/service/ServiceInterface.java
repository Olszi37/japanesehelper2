package pl.olszak.japanesehelper.japanesehelper.service;

import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ServiceInterface<ENTITY extends EntityInterface, DTO extends AbstractDTO, ID extends Serializable> {

    List<DTO> findAll();

    List<ENTITY> findAllEntities();

    Optional<DTO> findOne(ID id);

    ENTITY findOneEntity(ID id);

    DTO save(ENTITY entity);

    void delete(ENTITY entity);
}
