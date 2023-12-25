package com.example.dim.repo;

import com.example.dim.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer,Long> {
     List<Object[]> getResultExam(String studentName);
}
