package se331.project.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Folder;
import se331.project.rest.entity.Student;
import se331.project.rest.entity.Teacher;
import se331.project.rest.repository.AnnouncementRepository;
import se331.project.rest.repository.FolderRepository;
import se331.project.rest.repository.StudentRepository;
import se331.project.rest.repository.TeacherRepository;
import org.springframework.transaction.annotation.Transactional;
import se331.project.rest.security.user.Role;
import se331.project.rest.security.user.User;
import se331.project.rest.security.user.UserRepository;
import se331.project.rest.service.AnnouncementService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final StudentRepository studentRepository;
    final TeacherRepository teacherRepository;
    final UserRepository userRepository;
    final AnnouncementRepository announcementRepository;
    final FolderRepository folderRepository;
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        String jsonFilePath = "resource/cleaned_recipes.json"; // ระบุ path ที่เก็บไฟล์ JSON
        List<Announcement> recipes = null;
        try {
            recipes = convertJsonToEntities(jsonFilePath);
            announcementRepository.saveAll(recipes);
            System.out.println("Success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User admin;
        admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .roles(List.of(Role.ROLE_ADMIN))
                .build();
        userRepository.save(admin);

        Announcement announcement;
        announcement = Announcement.builder()
                .recipeId(38L)
                .name("Lowfat berry blue frozen dessert")
                .build();
        announcementRepository.save(announcement);

        Announcement announcement2;
        announcement2 = Announcement.builder()
                .recipeId(39L)
                .name("Biryani")
                .build();
        announcementRepository.save(announcement2);

        Announcement announcement3;
        announcement3 = Announcement.builder()
                .recipeId(40L)
                .name("Best Lemonade")
                .build();
        announcementRepository.save(announcement3);

        Announcement announcement4;
        announcement4 = Announcement.builder()
                .recipeId(41L)
                .name("Carina's tofu vegetable kebabs")
                .build();
        announcementRepository.save(announcement4);


        List<Announcement> announcementList1 = new ArrayList<>();
        announcementList1.add(announcement);
        announcementList1.add(announcement4);

        List<Announcement> announcementList2 = new ArrayList<>();
        announcementList2.add(announcement2);
        announcementList2.add(announcement3);

        Folder folder = Folder.builder()
                .id(1L)
                .name("Clean food")
                .ownAnnouncement(announcementList1)
                .build();
        folderRepository.save(folder);

        List<Folder> folderList1 = new ArrayList<>();
        folderList1.add(folder);

        Folder folder2 = Folder.builder()
                .id(2L)
                .name("For party")
                .ownAnnouncement(announcementList2)
                .build();
        folderRepository.save(folder2);

        List<Folder> folderList2 = new ArrayList<>();
        folderList2.add(folder2);

        announcement.setFolders(folderList1);
        announcementRepository.save(announcement);
        announcement2.setFolders(folderList2);
        announcementRepository.save(announcement2);
        announcement3.setFolders(folderList2);
        announcementRepository.save(announcement3);
        announcement4.setFolders(folderList1);
        announcementRepository.save(announcement4);

//        List<Folder> folders = new ArrayList<>();
//        folders.add(folder);
//        folders.add(folder2);

        User user;
        user = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("user@user.com")
                .roles(List.of(Role.ROLE_USER))
//                .folder(folders)
                .build();
        userRepository.save(user);

//        announcement.setFolders((List<Folder>) folder);
//        announcementRepository.save(announcement);
//        announcement2.setFolders((List<Folder>) folder2);
//        announcementRepository.save(announcement2);
//        announcement3.setFolders((List<Folder>) folder2);
//        announcementRepository.save(announcement3);
//        announcement4.setFolders((List<Folder>) folder);
//        announcementRepository.save(announcement4);

        folder.setUser(user);
        folderRepository.save(folder);
        folder2.setUser(user);
        folderRepository.save(folder2);
    }
    public static List<Announcement> convertJsonToEntities(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Announcement> recipes = objectMapper.readValue(new File(jsonFilePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Announcement.class));
        return recipes;
    }

//    public static List<Announcement> convertJsonToEntities(String jsonFilePath) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(Announcement.class, new CustomDeserializer());
//        objectMapper.registerModule(module);
//
//        List<Announcement> recipes = objectMapper.readValue(new File(jsonFilePath),
//                objectMapper.getTypeFactory().constructCollectionType(List.class, Announcement.class));
//        return recipes;
//    }
}
