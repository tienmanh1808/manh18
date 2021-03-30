package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ICourseRepository;
import com.example.demo.dao.ICourseStudentRepository;
import com.example.demo.dao.IStudentRepository;
import com.example.demo.entity.Course;
import com.example.demo.entity.CourseStudent;
import com.example.demo.entity.Student;

@Controller
@RequestMapping("/view")
public class StudentController {

	@Autowired
	IStudentRepository iStudentRepository;

	@Autowired
	ICourseRepository iCourseRepository;

	@Autowired
	ICourseStudentRepository iCourseStudentRepository;

	@RequestMapping(value="/students",method=RequestMethod.GET)
	public String developersList(Model model) {
		List<Student> listStudent = iStudentRepository.findAll();
		model.addAttribute("students", listStudent);
		return "view-infor";
	}

	@GetMapping("/showFormAdd")
	public String showFormAdd(Model theModel) {
		Student theStudent=new Student();
		theModel.addAttribute("student", theStudent);
		theModel.addAttribute("courses", iCourseRepository.findAll());
		return "addForm";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("student") Student studentAdd, @RequestParam Integer courseId) {
		List<Student> listStudentCheck = iStudentRepository.findAll();
		CourseStudent courseStudent = new CourseStudent();
		int temp =0;
		int idStudent = studentAdd.getId();
		for (Student checkStudent : listStudentCheck) {
			int idOld = checkStudent.getId();
			if(idStudent == idOld) {
				Student studentUp = iStudentRepository.findById(idStudent).get();
				courseStudent.setStudent(studentUp);
				temp =1 ;
				break;
			}
		}
		if(temp == 0) {
			courseStudent.setStudent(studentAdd);
		}
		iStudentRepository.save(studentAdd);
		if(courseId != 0) {
			Course course = iCourseRepository.findById(courseId).get();
			courseStudent.setCourse(course);
			iCourseStudentRepository.save(courseStudent);
		}							
		return "redirect:/view/students";
	}
	@GetMapping("/showFormUpdate")	
	public String showFormForUpdate(@RequestParam("theId") int studentID, Model theModel) {
		Student theStudent = iStudentRepository.findById(studentID).get();

		theModel.addAttribute("student", theStudent);
		//theModel.addAttribute("courses", iCourseRepository.findCourseUse(theID));
		List<Course> listCourses = iCourseRepository.findCourseNotUse(studentID);
		theModel.addAttribute("courses", listCourses);
		return "addForm";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("theId") int theId ) {
		Student student = iStudentRepository.findById(theId).get();

		List<CourseStudent> ListCourseStudents = iCourseStudentRepository.findAll();
		List<CourseStudent> courseStudents = new ArrayList<CourseStudent>();
		for(CourseStudent courseStudent : ListCourseStudents) {
			if(courseStudent.getStudent().getId() == theId) {
				courseStudents.add(courseStudent);
			}
		}
		for (CourseStudent courseStudent2 : courseStudents) {
			iCourseStudentRepository.deleteById(courseStudent2.getId());
		}
		
		iStudentRepository.deleteById(theId);
		return "redirect:/view/students";
	}


}
