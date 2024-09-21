package com.kuyajon.learningportal.model.course;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "questions")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    String questionText;

    @Column(nullable = false)
    private String choiceA;

    @Column(nullable = false)
    private String choiceB;

    @Column(nullable = false)
    private String choiceC;

    @Column(nullable = false)
    private String choiceD;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private AnswerChoice answer;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String solution;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
}
