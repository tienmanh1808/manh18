package com.example.demo.entity;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="shortdescription")
	private String shortDescription;
	

	@OneToMany(mappedBy = "course" ,cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<CourseStudent> students;
	public Course() {
	}

	public Course(String title, String shortDescription) {
		super();
		this.title = title;
		this.shortDescription = shortDescription;
	}

	public Course(int id, String title, String shortDescription) {
		super();
		this.id = id;
		this.title = title;
		this.shortDescription = shortDescription;
	}
	public String getTile() {
		return title;
	}
	public void setTile(String title) {
		this.title = title;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<CourseStudent> getStudents() {
		return students;
	}

	public void setStudents(List<CourseStudent> students) {
		this.students = students;
	}

}
