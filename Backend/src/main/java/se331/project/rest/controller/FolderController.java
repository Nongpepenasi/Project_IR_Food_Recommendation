package se331.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Folder;
import se331.project.rest.service.AnnouncementService;
import se331.project.rest.service.FolderService;
import se331.project.rest.util.LabMapper;

@RestController
@RequiredArgsConstructor
public class FolderController {
    final FolderService folderService;

    @GetMapping("folders")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getFolders() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getFolderDTO(folderService.getFolders()));
    }

//    @GetMapping("folder")
//    @CrossOrigin(origins = "http://localhost:3000")
//    public ResponseEntity<?> getFolder(@RequestParam(value = "_limit", required = false) Integer perPage,
//                                             @RequestParam(value = "_page", required = false) Integer page) {
//        perPage = perPage == null ? 3 : perPage;
//        page = page == null ? 1 : page;
//        Page<Folder> pageOutput;
//        pageOutput = folderService.getFolders(perPage,page);
//        HttpHeaders responseHeader = new HttpHeaders();
//        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
//        return new ResponseEntity<>(LabMapper.INSTANCE.getFolderDTO(pageOutput.getContent()),responseHeader, HttpStatus.OK);
//    }

    @GetMapping("folder/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getFolder(@PathVariable("id") Long id) {
        Folder output = folderService.getFolder(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getFolderDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("addFolder")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> addFolder(@RequestBody Folder folder) {
        return ResponseEntity.ok(LabMapper.INSTANCE.getFolderDTO(folderService.addFolder(folder)));
    }


    @PostMapping("deleteFolder/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> deleteFolder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(LabMapper.INSTANCE.getFolderDTO(folderService.deleteFolder(id)));
    }

//    @PostMapping("announcement")
//    public ResponseEntity<?> addAnnouncement(@RequestBody Announcement announcement) {
//        Announcement output = announcementService.save(announcement);
//        return ResponseEntity.ok(LabMapper.INSTANCE.getAnnouncementDTO(output));
//    }
}
