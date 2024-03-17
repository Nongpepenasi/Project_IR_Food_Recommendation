package se331.project.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.project.rest.entity.*;

import java.util.List;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
    List<StudentDTO> getStudentDTO(List<Student> students);
    @Mapping(source = "ownStudent", target = "ownStudent")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.firstname", target = "name")
    @Mapping(source = "user.lastname", target = "surname")
    @Mapping(source = "user.roles", target = "roles")
    @Mapping(source = "user.images", target = "images")
    @Mapping(source = "user.department", target = "department")
    @Mapping(source = "user.academic", target = "academic")
    TeacherDTO getTeacherDTO(Teacher teacher);
    List<TeacherDTO> getTeacherDTO(List<Teacher> teachers);
    @Mapping(source = "teacher", target = "teacher")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.firstname", target = "name")
    @Mapping(source = "user.lastname", target = "surname")
    @Mapping(source = "user.roles", target = "roles")
    @Mapping(source = "user.images", target = "images")
    @Mapping(source = "user.department", target = "department")
    StudentDTO getStudentDTO(Student student);

    AnnouncementDTO getAnnouncementDTO(Announcement announcement);
    @Mapping(source = "announcement", target = "announcement")
    @Mapping(source = "recipeId", target = "recipeId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "images", target = "images")
    @Mapping(source = "keywords", target = "keywords")
    @Mapping(source = "recipeIngredientQuantities", target = "recipeIngredientQuantities")
    @Mapping(source = "recipeIngredientParts", target = "recipeIngredientParts")
    @Mapping(source = "recipeInstructions", target = "recipeInstructions")
    List <AnnouncementDTO> getAnnouncementDTO(List<Announcement> announcement);

    FolderDTO getFolderDTO(Folder folder);

    @Mapping(source = "ownAnnouncement", target = "ownAnnouncement")
    @Mapping(source = "name", target = "name")
    List<FolderDTO> getFolderDTO(List<Folder> folder);
}
