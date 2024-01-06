package com.example.controller;

import com.example.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExamService examService;

    @GetMapping("/questionPercentage/{questionNumber}")
    public ResponseEntity<Map<String, String>> getQuestionStatistics(@PathVariable Integer questionNumber) {
        return examService.findQuestion(questionNumber);
    }

    @GetMapping("/result/{studentName}")
    public String studentResult(@PathVariable String studentName) {
        return examService.result(studentName);
    }

    @GetMapping("/examResult/{studentName}")
    public ResponseEntity<List<Map<String, String>>> getExamResults(@PathVariable String studentName) {
        return examService.getExamResults(studentName);
    }
}



