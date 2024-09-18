package com.kuyajon.learningportal.model.course;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lessons")
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private int order;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Topic> topics = new HashSet<>();

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Test test;
}
