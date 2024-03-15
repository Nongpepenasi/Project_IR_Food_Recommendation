package se331.project.rest.util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import se331.project.rest.entity.Announcement;
import se331.project.rest.entity.AnnouncementDTO;
import se331.project.rest.entity.Student;
import se331.project.rest.entity.StudentDTO;
import se331.project.rest.entity.StudentTeacherDTO;
import se331.project.rest.entity.Teacher;
import se331.project.rest.entity.TeacherDTO;
import se331.project.rest.entity.TeacherOwnStudentDTO;
import se331.project.rest.security.user.Role;
import se331.project.rest.security.user.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2567-03-15T14:35:11+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.2 (Oracle Corporation)"
)
public class LabMapperImpl implements LabMapper {

    @Override
    public List<StudentDTO> getStudentDTO(List<Student> students) {
        if ( students == null ) {
            return null;
        }

        List<StudentDTO> list = new ArrayList<StudentDTO>( students.size() );
        for ( Student student : students ) {
            list.add( getStudentDTO( student ) );
        }

        return list;
    }

    @Override
    public TeacherDTO getTeacherDTO(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        TeacherDTO.TeacherDTOBuilder teacherDTO = TeacherDTO.builder();

        teacherDTO.ownStudent( studentListToTeacherOwnStudentDTOList( teacher.getOwnStudent() ) );
        teacherDTO.username( teacherUserUsername( teacher ) );
        teacherDTO.name( teacherUserFirstname( teacher ) );
        teacherDTO.surname( teacherUserLastname( teacher ) );
        List<Role> roles = teacherUserRoles( teacher );
        List<Role> list1 = roles;
        if ( list1 != null ) {
            teacherDTO.roles( new ArrayList<Role>( list1 ) );
        }
        List<String> images = teacherUserImages( teacher );
        List<String> list2 = images;
        if ( list2 != null ) {
            teacherDTO.images( new ArrayList<String>( list2 ) );
        }
        teacherDTO.department( teacherUserDepartment( teacher ) );
        teacherDTO.academic( teacherUserAcademic( teacher ) );
        teacherDTO.id( teacher.getId() );

        return teacherDTO.build();
    }

    @Override
    public List<TeacherDTO> getTeacherDTO(List<Teacher> teachers) {
        if ( teachers == null ) {
            return null;
        }

        List<TeacherDTO> list = new ArrayList<TeacherDTO>( teachers.size() );
        for ( Teacher teacher : teachers ) {
            list.add( getTeacherDTO( teacher ) );
        }

        return list;
    }

    @Override
    public StudentDTO getStudentDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDTO.StudentDTOBuilder studentDTO = StudentDTO.builder();

        studentDTO.teacher( teacherToStudentTeacherDTO( student.getTeacher() ) );
        studentDTO.username( studentUserUsername( student ) );
        studentDTO.name( studentUserFirstname( student ) );
        studentDTO.surname( studentUserLastname( student ) );
        List<Role> roles = studentUserRoles( student );
        List<Role> list = roles;
        if ( list != null ) {
            studentDTO.roles( new ArrayList<Role>( list ) );
        }
        List<String> images = studentUserImages( student );
        List<String> list1 = images;
        if ( list1 != null ) {
            studentDTO.images( new ArrayList<String>( list1 ) );
        }
        studentDTO.department( studentUserDepartment( student ) );
        studentDTO.id( student.getId() );

        return studentDTO.build();
    }

    @Override
    public AnnouncementDTO getAnnouncementDTO(Announcement announcement) {
        if ( announcement == null ) {
            return null;
        }

        AnnouncementDTO.AnnouncementDTOBuilder announcementDTO = AnnouncementDTO.builder();

        announcementDTO.recipeId( announcement.getRecipeId() );
        announcementDTO.name( announcement.getName() );
        announcementDTO.authorId( announcement.getAuthorId() );
        announcementDTO.authorName( announcement.getAuthorName() );
        announcementDTO.cookTime( announcement.getCookTime() );
        announcementDTO.prepTime( announcement.getPrepTime() );
        announcementDTO.totalTime( announcement.getTotalTime() );
        announcementDTO.datePublished( announcement.getDatePublished() );
        List<String> list = announcement.getDescription();
        if ( list != null ) {
            announcementDTO.description( new ArrayList<String>( list ) );
        }
        List<String> list1 = announcement.getImages();
        if ( list1 != null ) {
            announcementDTO.images( new ArrayList<String>( list1 ) );
        }
        announcementDTO.recipeCategory( announcement.getRecipeCategory() );
        List<String> list2 = announcement.getKeywords();
        if ( list2 != null ) {
            announcementDTO.keywords( new ArrayList<String>( list2 ) );
        }
        List<String> list3 = announcement.getRecipeIngredientQuantities();
        if ( list3 != null ) {
            announcementDTO.recipeIngredientQuantities( new ArrayList<String>( list3 ) );
        }
        List<String> list4 = announcement.getRecipeIngredientParts();
        if ( list4 != null ) {
            announcementDTO.recipeIngredientParts( new ArrayList<String>( list4 ) );
        }
        announcementDTO.aggregatedRating( announcement.getAggregatedRating() );
        announcementDTO.reviewCount( announcement.getReviewCount() );
        announcementDTO.calories( announcement.getCalories() );
        announcementDTO.fatContent( announcement.getFatContent() );
        announcementDTO.saturatedFatContent( announcement.getSaturatedFatContent() );
        announcementDTO.cholesterolContent( announcement.getCholesterolContent() );
        announcementDTO.sodiumContent( announcement.getSodiumContent() );
        announcementDTO.carbohydrateContent( announcement.getCarbohydrateContent() );
        announcementDTO.fiberContent( announcement.getFiberContent() );
        announcementDTO.sugarContent( announcement.getSugarContent() );
        announcementDTO.proteinContent( announcement.getProteinContent() );
        announcementDTO.recipeServings( announcement.getRecipeServings() );
        announcementDTO.recipeYield( announcement.getRecipeYield() );
        List<String> list5 = announcement.getRecipeInstructions();
        if ( list5 != null ) {
            announcementDTO.recipeInstructions( new ArrayList<String>( list5 ) );
        }

        return announcementDTO.build();
    }

    @Override
    public List<AnnouncementDTO> getAnnouncementDTO(List<Announcement> announcement) {
        if ( announcement == null ) {
            return null;
        }

        List<AnnouncementDTO> list = new ArrayList<AnnouncementDTO>( announcement.size() );
        for ( Announcement announcement1 : announcement ) {
            list.add( getAnnouncementDTO( announcement1 ) );
        }

        return list;
    }

    protected TeacherOwnStudentDTO studentToTeacherOwnStudentDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        TeacherOwnStudentDTO.TeacherOwnStudentDTOBuilder teacherOwnStudentDTO = TeacherOwnStudentDTO.builder();

        teacherOwnStudentDTO.id( student.getId() );
        teacherOwnStudentDTO.name( student.getName() );
        teacherOwnStudentDTO.surname( student.getSurname() );

        return teacherOwnStudentDTO.build();
    }

    protected List<TeacherOwnStudentDTO> studentListToTeacherOwnStudentDTOList(List<Student> list) {
        if ( list == null ) {
            return null;
        }

        List<TeacherOwnStudentDTO> list1 = new ArrayList<TeacherOwnStudentDTO>( list.size() );
        for ( Student student : list ) {
            list1.add( studentToTeacherOwnStudentDTO( student ) );
        }

        return list1;
    }

    private String teacherUserUsername(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String teacherUserFirstname(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        String firstname = user.getFirstname();
        if ( firstname == null ) {
            return null;
        }
        return firstname;
    }

    private String teacherUserLastname(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        String lastname = user.getLastname();
        if ( lastname == null ) {
            return null;
        }
        return lastname;
    }

    private List<Role> teacherUserRoles(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        List<Role> roles = user.getRoles();
        if ( roles == null ) {
            return null;
        }
        return roles;
    }

    private List<String> teacherUserImages(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        List<String> images = user.getImages();
        if ( images == null ) {
            return null;
        }
        return images;
    }

    private String teacherUserDepartment(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        String department = user.getDepartment();
        if ( department == null ) {
            return null;
        }
        return department;
    }

    private String teacherUserAcademic(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        String academic = user.getAcademic();
        if ( academic == null ) {
            return null;
        }
        return academic;
    }

    protected StudentTeacherDTO teacherToStudentTeacherDTO(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        StudentTeacherDTO.StudentTeacherDTOBuilder studentTeacherDTO = StudentTeacherDTO.builder();

        studentTeacherDTO.id( teacher.getId() );
        studentTeacherDTO.name( teacher.getName() );
        studentTeacherDTO.surname( teacher.getSurname() );

        return studentTeacherDTO.build();
    }

    private String studentUserUsername(Student student) {
        if ( student == null ) {
            return null;
        }
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private String studentUserFirstname(Student student) {
        if ( student == null ) {
            return null;
        }
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        String firstname = user.getFirstname();
        if ( firstname == null ) {
            return null;
        }
        return firstname;
    }

    private String studentUserLastname(Student student) {
        if ( student == null ) {
            return null;
        }
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        String lastname = user.getLastname();
        if ( lastname == null ) {
            return null;
        }
        return lastname;
    }

    private List<Role> studentUserRoles(Student student) {
        if ( student == null ) {
            return null;
        }
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        List<Role> roles = user.getRoles();
        if ( roles == null ) {
            return null;
        }
        return roles;
    }

    private List<String> studentUserImages(Student student) {
        if ( student == null ) {
            return null;
        }
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        List<String> images = user.getImages();
        if ( images == null ) {
            return null;
        }
        return images;
    }

    private String studentUserDepartment(Student student) {
        if ( student == null ) {
            return null;
        }
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        String department = user.getDepartment();
        if ( department == null ) {
            return null;
        }
        return department;
    }
}