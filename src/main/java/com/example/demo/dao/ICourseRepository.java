package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Course;

public interface ICourseRepository extends JpaRepository<Course	, Integer> {

	@Query("select c from  Course c where c.id not in (select cs.course.id from  CourseStudent cs where cs.student.id = :theStudent)")
	public List<Course> findCourseNotUse(int theStudent);
}
