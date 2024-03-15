package se331.project.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Student;
import se331.project.rest.service.AnnouncementService;
import se331.project.rest.util.LabMapper;

@RestController
@RequiredArgsConstructor
public class AnnouncementController {
    final AnnouncementService announcementService;

    @GetMapping("announcements")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getAnnouncements() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getAnnouncementDTO(announcementService.getAnnouncements()));
    }

    @GetMapping("announcement")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getAnnouncement(@RequestParam(value = "_limit", required = false) Integer perPage,
                                             @RequestParam(value = "_page", required = false) Integer page) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Announcement> pageOutput;
        pageOutput = announcementService.getAnnouncements(perPage,page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getAnnouncementDTO(pageOutput.getContent()),responseHeader, HttpStatus.OK);
    }

    @GetMapping("announcement/{id}")
    public ResponseEntity<?> getAnnouncement(@PathVariable("id") Long id) {
        Announcement output = announcementService.getAnnouncement(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getAnnouncementDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
//    @PostMapping("announcement")
//    public ResponseEntity<?> addAnnouncement(@RequestBody Announcement announcement) {
//        Announcement output = announcementService.save(announcement);
//        return ResponseEntity.ok(LabMapper.INSTANCE.getAnnouncementDTO(output));
//    }
}
