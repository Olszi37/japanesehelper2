package pl.olszak.japanesehelper.japanesehelper.service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<DTO> {

    List<DTO> findAll();

    Optional<DTO> findById(Long id);

    DTO save(DTO dto);

    void delete(DTO dto);
}
