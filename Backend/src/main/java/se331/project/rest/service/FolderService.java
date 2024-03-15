package se331.project.rest.service;

import org.springframework.data.domain.Page;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Folder;

import java.util.List;

public interface FolderService {
    Integer getFolderSize();
    Folder save (Folder folder);
    Page<Folder> getFolders(Integer pageSize, Integer page);
    List<Folder> getFolders();
    Folder getFolder(Long id);
    Folder addFolder(Folder folder);
    Folder deleteFolder(Long id);
    Folder addRecipe(Long recipeId, Long id);
    Folder deleteRecipe(Long recipeId, Long id);
//    List<Announcement> saveRecipes(List<Announcement> recipes);
}
