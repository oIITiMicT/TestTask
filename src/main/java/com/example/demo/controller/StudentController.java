package com.example.demo.controller;


import com.example.demo.builder.NewStudentBuilder;
import com.example.demo.builder.StudentResponseBuilder;
import com.example.demo.dto.StudentFormDto;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    private final StudentResponseBuilder studentResponseBuilder;

    private final NewStudentBuilder studentBuilder;


    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Student student = studentService.findStudentById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return new ResponseEntity<>(studentResponseBuilder.buildStudentResponse(student), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewStudent(@RequestBody StudentFormDto studentFormDto) {
        Student student = studentBuilder.buildStudent(studentFormDto);
        student = studentService.saveStudent(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody StudentFormDto studentFormDto) {
        return null;
    }
}
