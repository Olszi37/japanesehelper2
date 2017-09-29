package pl.olszak.japanesehelper.japanesehelper.converter;

public interface Converter<ENTITY, DTO> {

    ENTITY convertToEntity(DTO dto);

    DTO convertToDTO(ENTITY entity);
}
