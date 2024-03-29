package se331.project.rest.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import se331.project.rest.entity.Student;

import java.util.List;


public interface StudentService {

    Integer getStudentsSize();

    Page<Student> getStudents(Integer pageSize, Integer page);

    Page<Student> getStudents(String filter, Pageable pageable);
    List<Student> getAllStudents();
    List<Student> getStudentsByTeacher(Long id);

    Student getStudent(Long id);

    Student save(Student student);

    Student updateDetail(Student student);

}
