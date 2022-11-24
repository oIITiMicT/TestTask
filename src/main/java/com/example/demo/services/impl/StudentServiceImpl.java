package com.example.demo.services.impl;

import com.example.demo.exception.IllegalRequestParamException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student addTeacherToStudent(Student student, Teacher teacher) {
        student.addTeacher(teacher);
        saveStudent(student);
        return student;
    }

    @Override
    public Student deleteTeacherFromStudent(Student student, Teacher teacher) {
        student.deleteTeacher(teacher);
        saveStudent(student);
        return student;
    }

    private void checkField(String sortBy) {
        Field[] fields = Student.class.getDeclaredFields();
        boolean flag = false;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(sortBy)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new IllegalRequestParamException("cannot find field: ", sortBy);
        }
    }

    private void checkPageParams(Long page, Long number) {
        if (page < 0) {
            throw new IllegalRequestParamException("Page params incorrect: ", Long.toString(page));
        }

        if (number < 1) {
            throw new IllegalRequestParamException("Page params incorrect: ", Long.toString(number));
        }
    }

    @Override
    public Page<Student> getPageOfStudents(Long page, Long number) {
        checkPageParams(page, number);
        Pageable pageable = PageRequest.of(page.intValue(), number.intValue());
        return studentRepository.findAll(pageable);
    }

    @Override
    public List<Student> getSortedListOfStudents(String sortBy) {
        checkField(sortBy);
        return studentRepository.findAll(Sort.by(sortBy));
    }

    @Override
    public List<Student> getListOfStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Set<Teacher> getAssociatedTeachers(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(Long.toString(studentId))).getTeachers();
    }

    @Override
    public List<Student> findStudentsByFirstnameAndLastname(String firstName, String lastName) {
        if (firstName == null && lastName == null) {
            return studentRepository.findAll();
        }

        if (firstName == null) {
            return studentRepository.findStudentsByLastName(lastName);
        }

        if (lastName == null) {
            return studentRepository.findStudentsByFirstName(firstName);
        }

        return studentRepository.findStudentsByFirstNameAndLastName(firstName, lastName);
    }
}
