package com.kuyajon.learningportal.model.course;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tests")
@Data
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id", nullable = true)
    private Lesson lesson;

    @OneToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = true)
    private Topic topic;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Question> questions = new HashSet<>();
}
