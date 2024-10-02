package com.kuyajon.learningportal.dto.course;

import lombok.Data;

@Data
public class TopicDTO {
    private Long id;
    private String content;
    private String name;

    private Long lessonId;
}
