package com.example.dim.repo;

import com.example.dim.entity.ExamAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExamAnswersRepository extends JpaRepository<ExamAnswers,Long> {
}
