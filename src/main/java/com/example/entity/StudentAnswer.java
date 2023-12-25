package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "StudentAnswer.getQuestionStatistics",

        query = "SELECT CONCAT( CONCAT(ROUND(SUM(CASE WHEN sa.answerByStudent = 'A' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2), '%'),(case when ex.correctAnswer='A'then' ✅'else ''end ))AS A," +
                "       CONCAT( CONCAT(ROUND(SUM(CASE WHEN sa.answerByStudent = 'B' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2), '%'),(case when ex.correctAnswer='B'then' ✅'else ''end ))AS B," +
                "       CONCAT( CONCAT(ROUND(SUM(CASE WHEN sa.answerByStudent = 'C' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2), '%'),(case when ex.correctAnswer='C'then' ✅'else ''end ))AS C," +
                "       CONCAT( CONCAT(ROUND(SUM(CASE WHEN sa.answerByStudent = 'D' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2), '%'),(case when ex.correctAnswer='D'then' ✅'else ''end ))AS D," +
                "       CONCAT( CONCAT(ROUND(SUM(CASE WHEN sa.answerByStudent = 'E' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2), '%'),(case when ex.correctAnswer='E'then' ✅'else ''end ))AS E," +
                "       CONCAT(ROUND(SUM(CASE WHEN sa.answerByStudent IS NULL THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2), '%') AS F " +
                "FROM StudentAnswer sa,ExamAnswers ex WHERE ex.question = questionNumber and sa.questionNumber=:questionNumber")
@NamedQuery(name = "StudentAnswer.getResultExam", query = "SELECT sa.questionNumber,\n" +
        "       sa.answerByStudent,\n" +
        "       ex.correctAnswer,\n" +
        "      ( case  when  sa.answerByStudent = ex.correctAnswer then 'true' else 'false' end) AS result\n" +
        "FROM StudentAnswer sa\n" +
        "         JOIN ExamAnswers ex ON sa.questionNumber = ex.question\n" +
        "WHERE sa.studentName = :studentName")

@Entity
@Table(name = "student_answer")
@Getter
@Setter
public class StudentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String studentName;
    private String answerByStudent;
    private Integer questionNumber;
}
