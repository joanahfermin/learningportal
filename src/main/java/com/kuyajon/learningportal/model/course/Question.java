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

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = true) // nullable = true by default
    private Topic topic;

    // Many-to-one relationship with Lesson (optional)
    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = true) // nullable = true by default
    private Lesson lesson;
}
