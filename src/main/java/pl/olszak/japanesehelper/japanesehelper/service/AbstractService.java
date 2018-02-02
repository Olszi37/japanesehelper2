package pl.olszak.japanesehelper.japanesehelper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.olszak.japanesehelper.japanesehelper.converter.Converter;
import pl.olszak.japanesehelper.japanesehelper.domain.EntityInterface;
import pl.olszak.japanesehelper.japanesehelper.dto.AbstractDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public abstract class AbstractService<ENTITY extends EntityInterface, DTO extends AbstractDTO, ID extends Serializable> {

    private Converter<ENTITY, DTO> converter;

    private JpaRepository<ENTITY, ID> repository;

    @Autowired
    public AbstractService(Converter<ENTITY, DTO> converter, JpaRepository<ENTITY, ID> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<DTO> findAll(){
        return repository.findAll().stream().map(converter::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ENTITY> findAllEntities(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<DTO> findOne(ID id){
        Optional<ENTITY> entity = Optional.of(repository.findOne(id));
        return entity.map(converter::convertToDTO);
    }

    @Transactional(readOnly = true)
    public ENTITY findOneEntity(ID id){
        return repository.findOne(id);
    }

    public DTO save(ENTITY entity){
        ENTITY saved = repository.save(entity);
        return converter.convertToDTO(saved);
    }

    public void delete(ENTITY entity){
        repository.delete(entity);
    }
}
