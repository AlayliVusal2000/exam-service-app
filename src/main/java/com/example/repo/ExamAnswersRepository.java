package com.example.repo;

import com.example.entity.ExamAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExamAnswersRepository extends JpaRepository<ExamAnswers,Long> {
}
