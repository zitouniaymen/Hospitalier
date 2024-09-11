package com.bezkoder.spring.jpa.h2.mapper;

import com.bezkoder.spring.jpa.h2.dtos.TutorialDto;
import com.bezkoder.spring.jpa.h2.model.Tutorial;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface TutorialMapper {
    TutorialMapper MAPPER= Mappers.getMapper(TutorialMapper.class);

    TutorialDto tutorialToTutorialDto(Tutorial tutorial);
    Tutorial tutorialDtoToTutorial(TutorialDto tutorialDto);
}
