package com.kuyajon.learningportal.dto.course;

import lombok.Data;

@Data
public class CourseDTO {
    private Long id;

    private String name;

    private String description = "";

}
