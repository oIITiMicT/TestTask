package com.example.demo.services.impl;

import com.example.demo.exception.IllegalRequestParamException;
import com.example.demo.exception.TeacherNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.services.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Optional<Teacher> findTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher addStudentToTeacher(Teacher teacher, Student student) {
        teacher.addStudent(student);
        saveTeacher(teacher);
        return teacher;
    }

    @Override
    public Teacher deleteStudentFromTeacher(Teacher teacher, Student student) {
        teacher.deleteStudent(student);
        saveTeacher(teacher);
        return teacher;
    }

    private void checkField(String sortBy) {
        Field[] fields = Teacher.class.getDeclaredFields();
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
    public Page<Teacher> getPageOfTeachers(Long page, Long number) {
        checkPageParams(page, number);
        Pageable pageable = PageRequest.of(page.intValue(), number.intValue());
        return teacherRepository.findAll(pageable);
    }

    @Override
    public List<Teacher> getSortedListOfTeachers(String sortBy) {
        checkField(sortBy);
        return teacherRepository.findAll(Sort.by(sortBy));
    }

    @Override
    public List<Teacher> getListOfTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Set<Student> getAssociatedStudents(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() -> new TeacherNotFoundException(Long.toString(teacherId))).getStudents();
    }

    @Override
    public List<Teacher> findTeachersByFirstnameAndLastname(String firstName, String lastName) {
        if (firstName == null && lastName == null) {
            return teacherRepository.findAll();
        }

        if (firstName == null) {
            return teacherRepository.findTeachersByLastName(lastName);
        }

        if (lastName == null) {
            return teacherRepository.findTeachersByFirstName(firstName);
        }

        return teacherRepository.findTeachersByFirstNameAndLastName(firstName, lastName);
    }
}
