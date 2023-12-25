package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "exam_answers")
@Getter
@Setter
public class ExamAnswers {
    @Column(unique = true)
    private Integer question;
    private String correctAnswer;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
