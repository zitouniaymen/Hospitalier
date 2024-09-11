package com.bezkoder.spring.jpa.h2.service;

import com.bezkoder.spring.jpa.h2.dtos.TutorialDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TutorialService {
	 List<TutorialDto> findTutorialsWithTitle(String title);
	 Page<TutorialDto> findAllTutorials(Pageable pageable);
	 TutorialDto findTutorialsById(Long id);
	 void saveTutorial(TutorialDto tutorialDto);
}
