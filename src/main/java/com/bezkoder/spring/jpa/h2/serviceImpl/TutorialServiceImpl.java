package com.bezkoder.spring.jpa.h2.serviceImpl;

import com.bezkoder.spring.jpa.h2.dtos.TutorialDto;
import com.bezkoder.spring.jpa.h2.exceptions.TitleNotFoundException;
import com.bezkoder.spring.jpa.h2.exceptions.TutorialNotFoundException;
import com.bezkoder.spring.jpa.h2.mapper.TutorialMapper;
import com.bezkoder.spring.jpa.h2.model.Tutorial;
import com.bezkoder.spring.jpa.h2.repository.TutorialRepository;
import com.bezkoder.spring.jpa.h2.service.TutorialService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorialServiceImpl implements TutorialService {

    private static final Logger logger = LoggerFactory.getLogger(TutorialServiceImpl.class);
    private final TutorialRepository tutorialRepository;
    private final TutorialMapper tutorialMapper;

    public TutorialServiceImpl(TutorialRepository tutorialRepository, TutorialMapper tutorialMapper) {
        this.tutorialRepository = tutorialRepository;
        this.tutorialMapper = tutorialMapper;
    }
    
    
@Override
    public List<TutorialDto> findTutorialsWithTitle(String title) {

        try {

            if (title == null || title.trim().isEmpty()) {
                logger.warn("The title provided is null or empty");
                throw new IllegalArgumentException("Title cannot be null or empty");
            }


            logger.info("start serach tutorial by {}", title);
            List<Tutorial> tutorials = tutorialRepository.findByTitleContainingIgnoreCase(title);

            if (tutorials.isEmpty()) {

                logger.info("No tutorials found with title: {}", title);
                throw new TitleNotFoundException(title);
            }
            return tutorials.stream().map(tutorialMapper::tutorialToTutorialDto).collect(Collectors.toList());
        } catch (DataAccessException e) {

            logger.error("Database error while searching for tutorials", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred", e);


        } catch (Exception e) {

            logger.error("Unexpected error while searching for tutorials", e);
            throw new TitleNotFoundException("Internal error occurred", e.getCause());
        }
    }


    public Page<TutorialDto> findAllTutorials(Pageable pageable) {

        try {
            logger.info("start get All tutorial by ");
            Page<Tutorial> tutorialsPage = tutorialRepository.findAll(pageable);
            if (tutorialsPage.isEmpty()) {

                logger.info("No tutorials found :");

            } else {

                logger.info("{} tutorials found on page {} of {}.",
                        tutorialsPage.getNumberOfElements(),
                        tutorialsPage.getNumber() + 1,
                        tutorialsPage.getTotalPages());
            }
            List<TutorialDto> tutorialDtos = tutorialsPage.stream().map(tutorialMapper::tutorialToTutorialDto).collect(Collectors.toList());

            return new PageImpl<>(tutorialDtos, pageable, tutorialsPage.getTotalElements());
        } catch (DataAccessException e) {

            logger.error("Database error while get for tutorials", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred", e);

        }

    }

    public TutorialDto findTutorialsById(Long id) {

        try {

            logger.info("start serach tutorial by {}", id);
            Tutorial tutorial = tutorialRepository.findTutorialById(id).orElseThrow(
                    () -> {
                        logger.info("No tutorials found with id: {}", id);
                        return new TitleNotFoundException(String.valueOf(id));
                    });
            return this.tutorialMapper.tutorialToTutorialDto(tutorial);
        } catch (DataAccessException e) {

            logger.error("Database error while searching for tutorials", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred", e);

        } catch (Exception e) {

            logger.error("Unexpected error while searching for tutorials", e);
            throw new TutorialNotFoundException("Internal error occurred", e.getCause());
        }
    }

    @Transactional
    public void saveTutorial(TutorialDto tutorialDto) {
        try {

            logger.info("start save tutorial");
            validateTutoril(tutorialDto);
            Tutorial tutorial = tutorialMapper.tutorialDtoToTutorial(tutorialDto);
            tutorialRepository.save(tutorial);
            logger.info("Succeful  save tutorial");
        } catch (DataAccessException e) {

            logger.error("Database error while searching for tutorials", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Database error occurred", e);
        }
    }

    public void validateTutoril(TutorialDto tutorial) {

        if (tutorial == null) {

            logger.warn("The tutorial provided is null or empty");
            throw new IllegalArgumentException("tutorial cannot be null or empty");
        } else if (tutorial.title().trim() == null || tutorial.title().trim().isEmpty()) {

            logger.warn("The title provided is null or empty");
            throw new IllegalArgumentException("Title cannot be null or empty");

        } else if (tutorial.description().trim() == null || tutorial.description().trim().isEmpty()) {

            logger.warn("The Description provided is null or empty");
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}
