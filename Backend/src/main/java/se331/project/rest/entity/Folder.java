package se331.project.rest.entity;

import jakarta.persistence.*;
import lombok.*;
import se331.project.rest.security.user.User;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    @ManyToMany(mappedBy = "folders")
    @Builder.Default
    List<Announcement> ownAnnouncement = new ArrayList<>();
    @ManyToOne
    User user;
}
