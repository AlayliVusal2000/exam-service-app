package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedQuery(name = "Student.examResult", query = "select round(cast( count(ex.correctAnswer)as double )* 100 / 29, 2) as result\n" +
        "from StudentAnswer sa\n" +
        "         join ExamAnswers ex on sa.questionNumber = ex.question\n" +
        "    and sa.answerByStudent = ex.correctAnswer\n" +
        "where sa.studentName = :studentName")
@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    private String name;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
}
