package se331.project.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.project.rest.entity.Announcement;

import java.util.List;

public interface AnnouncementDao {
    Integer getAnnouncementSize();
    Announcement save (Announcement announcement);
    Page<Announcement> getAnnouncements(Integer pageSize, Integer page);
    Page<Announcement> getAnnouncements(Pageable page);
    Announcement getAnnouncement(Long id);
    List<Announcement> saveRecipes(List<Announcement> recipes);
}
