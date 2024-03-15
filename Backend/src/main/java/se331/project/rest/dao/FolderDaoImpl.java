package se331.project.rest.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Folder;
import se331.project.rest.repository.FolderRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class FolderDaoImpl implements FolderDao {
    final FolderRepository folderRepository;
    @Override
    public Integer getFolderSize() {
        return Math.toIntExact(folderRepository.count());
    }

    @Override
    public Folder save(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public Page<Folder> getFolders(Integer pageSize, Integer page) {
        return folderRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Page<Folder> getFolders(Pageable page) {
        return folderRepository.findAll(page);
    }

    @Override
    public Folder getFolder(Long id) {
        return folderRepository.findById(id).orElse(null);
    }

    @Override
    public Folder addFolder(Folder folder){
        return folderRepository.save(folder);
    }

    @Override
    public Folder deleteFolder(Long id) {
        folderRepository.deleteById(id);
        return null;
    }

//    @Override
//    public List<Folder> saveRecipes(List<Folder> recipes){
//        return folderRepository.saveAll(recipes);
//    }

}
