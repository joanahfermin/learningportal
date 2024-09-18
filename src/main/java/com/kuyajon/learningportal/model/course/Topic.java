package com.kuyajon.learningportal.model.course;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "topics")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content = "";

    @Column(nullable = false)
    private int order;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @OneToOne(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Test test;
}
