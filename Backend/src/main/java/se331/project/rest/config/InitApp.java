package se331.project.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.Student;
import se331.project.rest.entity.Teacher;
import se331.project.rest.repository.AnnouncementRepository;
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
    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

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


        String jsonFilePath = "resource/limited_recipes.json"; // ระบุ path ที่เก็บไฟล์ JSON
        List<Announcement> recipes = null;
        try {
            recipes = convertJsonToEntities(jsonFilePath);
            announcementRepository.saveAll(recipes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        List<String> files = new ArrayList<>();
//
//        files.add("https://rb.gy/1jp8m");
//        files.add("https://rebrand.ly/xz81uz9");

//        Announcement announcement;
//        announcement = Announcement.builder()
//                .title("Final Exam")
//                .description("Final Exam is tomorrow.")
//                .files(files)
//                .build();
//        announcementRepository.save(announcement);



    }
    public static List<Announcement> convertJsonToEntities(String jsonFilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Announcement> recipes = objectMapper.readValue(new File(jsonFilePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Announcement.class));
        return recipes;
    }
}
