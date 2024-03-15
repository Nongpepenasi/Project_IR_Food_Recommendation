package se331.project.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Folder;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
