package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.CourseStudent;

public interface ICourseStudentRepository extends JpaRepository<CourseStudent, Integer> {

//	@Transactional
//	@Modifying
//	@Query(value="insert into CourseStudent(student.id,course.id) values (:studentId,:courseId)")
//	public void addCourseStudent(int studentId, int courseId);

}
