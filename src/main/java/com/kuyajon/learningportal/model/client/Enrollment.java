package com.kuyajon.learningportal.model.client;

import com.kuyajon.learningportal.model.course.Course;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "enrollments")
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private EProgressStatus status = EProgressStatus.NOT_STARTED;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LessonProgress> lessonProgresses = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TopicProgress> topicProgresses = new HashSet<>();
}
