package com.example.demo.controller;

import com.example.demo.builder.NewTeacherBuilder;
import com.example.demo.builder.TeacherResponseBuilder;
import com.example.demo.dto.TeacherFormDto;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.exception.TeacherNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import com.example.demo.validation.TeacherFormValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    private final StudentService studentService;

    private final TeacherResponseBuilder teacherResponseBuilder;

    private final NewTeacherBuilder teacherBuilder;

    private final TeacherFormValidation teacherFormValidation;


    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.findTeacherById(id).orElseThrow(() -> new TeacherNotFoundException(Long.toString(id)));
        return new ResponseEntity<>(teacherResponseBuilder.buildTeacherResponse(teacher), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewTeacher(@RequestBody TeacherFormDto teacherFormDto) {
        Teacher teacher = teacherBuilder.buildTeacher(teacherFormDto);
        teacher = teacherService.saveTeacher(teacher);
        return new ResponseEntity<>(teacherResponseBuilder.buildTeacherResponse(teacher), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTeacher(@RequestBody TeacherFormDto teacherFormDto) {
        Teacher teacher = teacherFormValidation.validateUpdateTeacherFormAndBuild(teacherFormDto);
        teacher = teacherService.saveTeacher(teacher);
        return new ResponseEntity<>(teacherResponseBuilder.buildTeacherResponse(teacher), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacherById(@PathVariable Long id) {
        if (teacherService.findTeacherById(id).isEmpty()) {
            throw new TeacherNotFoundException(Long.toString(id));
        }
        teacherService.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{teacherId}/students/{studentId}")
    public ResponseEntity<?> addStudentToTeacher(@PathVariable Long teacherId, @PathVariable Long studentId) {
        Teacher teacher = teacherService.findTeacherById(teacherId).orElseThrow(() -> new TeacherNotFoundException(Long.toString(teacherId)));
        Student student = studentService.findStudentById(studentId).orElseThrow(() -> new StudentNotFoundException(Long.toString(studentId)));
        teacher = teacherService.addStudentToTeacher(teacher, student);
        return new ResponseEntity<>(teacherResponseBuilder.buildTeacherResponse(teacher), HttpStatus.OK);
    }

    @DeleteMapping("/{teacherId}/students/{studentId}")
    public ResponseEntity<?> deleteTeacherFromStudent(@PathVariable Long teacherId, @PathVariable Long studentId) {
        Teacher teacher = teacherService.findTeacherById(teacherId).orElseThrow(() -> new TeacherNotFoundException(Long.toString(teacherId)));
        Student student = studentService.findStudentById(studentId).orElseThrow(() -> new StudentNotFoundException(Long.toString(studentId)));
        teacher = teacherService.deleteStudentFromTeacher(teacher, student);
        return new ResponseEntity<>(teacherResponseBuilder.buildTeacherResponse(teacher), HttpStatus.OK);
    }
}
