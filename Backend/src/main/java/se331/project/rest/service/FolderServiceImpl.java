package se331.project.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.project.rest.dao.AnnouncementDao;
import se331.project.rest.dao.FolderDao;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Folder;
import se331.project.rest.entity.Teacher;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    final FolderDao folderDao;
    final AnnouncementDao announcementDao;
    @Override
    public Integer getFolderSize() {
        return folderDao.getFolderSize();
    }

    @Override
    public Folder save(Folder folder) {
        return folderDao.save(folder);
    }

    @Override
    public Page<Folder> getFolders(Integer pageSize, Integer page) {
        return folderDao.getFolders(pageSize, page);
    }

    @Override
    public List<Folder> getFolders() {
        return folderDao.getFolders(Pageable.unpaged()).getContent();
    }

    @Override
    public Folder getFolder(Long id) {
        return folderDao.getFolder(id);
    }

    @Override
    public Folder addFolder(Folder folder) {
        return folderDao.addFolder(folder);
    }

    @Override
    public Folder deleteFolder(Long id) {
        return folderDao.deleteFolder(id);
    }

    @Override
    public Folder addRecipe(Long recipeId, Long id) {
        Folder updateFolder = folderDao.getFolder(id);
        Announcement updateAnnouncement = announcementDao.getAnnouncement(recipeId);
        if (updateFolder != null) {
            updateFolder.getOwnAnnouncement().add(announcementDao.getAnnouncement(recipeId));
            updateAnnouncement.getFolders().add(updateFolder);

            folderDao.save(updateFolder);
            announcementDao.save(updateAnnouncement);
            return updateFolder;
        }
        return null;
    }

    @Override
    public Folder deleteRecipe(Long recipeId, Long id) {
        Folder updateFolder = folderDao.getFolder(id);
        Announcement updateAnnouncement = announcementDao.getAnnouncement(recipeId);
        if (updateFolder != null) {
            updateFolder.getOwnAnnouncement().remove(announcementDao.getAnnouncement(recipeId));
            updateAnnouncement.getFolders().remove(updateFolder);

            folderDao.save(updateFolder);
            announcementDao.save(updateAnnouncement);
            return updateFolder;
        }
        return null;
    }

//    @Override
//    public List<Announcement> saveRecipes(List<Announcement> recipes) {
//        return announcementDao.saveRecipes(recipes);
//    }
}
