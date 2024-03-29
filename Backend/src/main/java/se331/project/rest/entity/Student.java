package se331.project.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import se331.project.rest.security.user.User;

import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String surname;
    @ElementCollection
    List<String> images;
    String department;
    @ManyToOne
    Teacher teacher;
    @OneToOne
    User user;
}
