package com.bezkoder.spring.jpa.h2.dtos;

import lombok.Getter;
import lombok.Setter;


public record TutorialDto(Long id, String title, String description) {
}
