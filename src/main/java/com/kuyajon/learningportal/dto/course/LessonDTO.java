package com.kuyajon.learningportal.dto.course;

import lombok.Data;

@Data
public class LessonDTO {
    private Long id;
    private String name;
    private String description = "";
    private Long courseId;
}
