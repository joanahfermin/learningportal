package com.kuyajon.learningportal.dto.course;

import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String answer;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;

    private String questionText;
    private String solution;
    private Long testId;
}
