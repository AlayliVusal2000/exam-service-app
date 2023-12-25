package com.example.service;

import com.example.repo.StudentAnswerRepository;
import com.example.repo.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final StudentAnswerRepository studentAnswerRepository;
    private final StudentRepository studentRepository;
    private final EntityManager entityManager;

    public String result(String studentName) {
        return studentName + "'s exam result is: " + studentRepository.examResult(studentName);
    }

    public ResponseEntity<List<Map<String, String>>> getExamResults(@PathVariable String studentName) {
        List<Object[]> examResults = studentAnswerRepository.getResultExam(studentName);
        List<Map<String, String>> resultMaps = examResults.stream()
                .map(item -> {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("Question number", String.valueOf(item[0]));
                    map.put("Answer by student", (String) item[1]);
                    map.put("Correct question", (String) item[2]);
                    map.put("Result", (String) item[3]);
                    return map;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultMaps);
    }

    public ResponseEntity<Map<String, String>> findQuestion(Integer questionNumber) {
        TypedQuery<Object[]> query = entityManager.createNamedQuery("StudentAnswer.getQuestionStatistics", Object[].class);
        query.setParameter("questionNumber", questionNumber);
        try {
            Object[] result = query.getSingleResult();
            Map<String, String> statisticsMap = new LinkedHashMap<>();
            statisticsMap.put("Option A: ", result[0].toString());
            statisticsMap.put("Option B: ", result[1].toString());
            statisticsMap.put("Option C: ", result[2].toString());
            statisticsMap.put("Option D: ", result[3].toString());
            statisticsMap.put("Option E: ", result[4].toString());
            statisticsMap.put("Disclaimers: ", result[5].toString());
            return new ResponseEntity<>(statisticsMap, HttpStatus.OK);
        } catch (
                NoResultException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
