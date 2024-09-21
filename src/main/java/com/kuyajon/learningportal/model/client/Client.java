package com.kuyajon.learningportal.model.client;

import com.kuyajon.learningportal.model.sys.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Link to the User entity for login

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "client_groups",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<ClientGroup> groups = new HashSet<>();  // Many-to-many relationship with ClientGroup
}
