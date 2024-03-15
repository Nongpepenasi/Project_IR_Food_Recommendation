package se331.project.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Folder;

import java.util.List;

public interface FolderDao {
    Integer getFolderSize();
    Folder save (Folder folder);
    Page<Folder> getFolders(Integer pageSize, Integer page);
    Page<Folder> getFolders(Pageable page);
    Folder getFolder(Long id);
    Folder addFolder(Folder folder);
    Folder deleteFolder(Long id);
//    List<Folder> saveRecipes(List<Announcement> recipes);
}
