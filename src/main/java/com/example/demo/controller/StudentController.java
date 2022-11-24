package com.example.demo.controller;


import com.example.demo.builder.NewStudentBuilder;
import com.example.demo.builder.StudentResponseBuilder;
import com.example.demo.dto.StudentFormDto;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.exception.TeacherNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import com.example.demo.validation.StudentFormValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    private final TeacherService teacherService;

    private final StudentResponseBuilder studentResponseBuilder;

    private final NewStudentBuilder studentBuilder;

    private final StudentFormValidation studentFormValidation;


    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = studentService.findStudentById(id).orElseThrow(() -> new StudentNotFoundException(Long.toString(id)));
        return new ResponseEntity<>(studentResponseBuilder.buildStudentResponse(student), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewStudent(@RequestBody StudentFormDto studentFormDto) {
        Student student = studentBuilder.buildStudent(studentFormDto);
        student = studentService.saveStudent(student);
        return new ResponseEntity<>(studentResponseBuilder.buildStudentResponse(student), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody StudentFormDto studentFormDto) {
        Student student = studentFormValidation.validateUpdateStudentFormAndBuild(studentFormDto);
        student = studentService.saveStudent(student);
        return new ResponseEntity<>(studentResponseBuilder.buildStudentResponse(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable Long id) {
        if (studentService.findStudentById(id).isEmpty()) {
            throw new StudentNotFoundException(Long.toString(id));
        }
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{studentId}/teachers/{teacherId}")
    public ResponseEntity<?> addTeacherToStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        Student student = studentService.findStudentById(studentId).orElseThrow(() -> new StudentNotFoundException(Long.toString(studentId)));
        Teacher teacher = teacherService.findTeacherById(teacherId).orElseThrow(() -> new TeacherNotFoundException(Long.toString(teacherId)));
        student = studentService.addTeacherToStudent(student, teacher);
        return new ResponseEntity<>(studentResponseBuilder.buildStudentResponse(student), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/teachers/{teacherId}")
    public ResponseEntity<?> deleteTeacherFromStudent(@PathVariable Long studentId, @PathVariable Long teacherId) {
        Student student = studentService.findStudentById(studentId).orElseThrow(() -> new StudentNotFoundException(Long.toString(studentId)));
        Teacher teacher = teacherService.findTeacherById(teacherId).orElseThrow(() -> new TeacherNotFoundException(Long.toString(teacherId)));
        student = studentService.deleteTeacherFromStudent(student, teacher);
        return new ResponseEntity<>(studentResponseBuilder.buildStudentResponse(student), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getPageOfStudents(@RequestParam(required = false, name="page") Long page,
                                               @RequestParam(required = false, name="number") Long number,
                                               @RequestParam(required = false, name="sortBy") String sortBy) {
        if (sortBy == null && (page == null || number == null)) {
            return new ResponseEntity<>(studentService.getListOfStudents(), HttpStatus.OK);
        }
        if (sortBy == null) {
            return new ResponseEntity<>(studentService.getPageOfStudents(page, number), HttpStatus.OK);
        }
        return new ResponseEntity<>(studentService.getSortedListOfStudents(sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}/teachers")
    public ResponseEntity<?> getListOfTeachersAssociatedWithStudent(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getAssociatedTeachers(id), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<?> findStudentsByFirstNameAndLastName(@RequestParam(required = false, name = "firstname") String firstName,
                                                                @RequestParam(required = false, name = "lastname") String lastName) {
        return new ResponseEntity<>(studentService.findStudentsByFirstnameAndLastname(firstName, lastName), HttpStatus.OK);
    }
}
