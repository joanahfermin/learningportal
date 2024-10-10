package com.kuyajon.learningportal.dto.course;

import com.kuyajon.learningportal.model.course.AnswerChoice;

import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    String questionText;

    private String choiceA;

    private String choiceB;

    private String choiceC;

    private String choiceD;

    private AnswerChoice answer;

    private String solution;

    private Long topicId;

    private Long lessonId;

}
