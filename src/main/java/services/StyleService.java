
package services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StyleRepository;
import domain.Course;
import domain.Style;

@Service
@Transactional
public class StyleService {

	//Repositories
	@Autowired
	private StyleRepository styleRepository;

	//Services


	//Constructor
	public StyleService() {
		super();
	}

	//CRUD Methods

	public Style create() {
		Style style = new Style();
		style.setCourses(new LinkedList<Course>());
		style.setDescription("");
		style.setName("");
		style.setPictures(new LinkedList<String>());
		style.setVideos(new LinkedList<String>());

		return style;
	}

	public void delete(Style style) {
		Assert.notNull(style);
		Assert.isTrue(style.getCourses().isEmpty());
		
		styleRepository.delete(style);
	}
	
	public List<Style> findAll() {
		return styleRepository.findAll();
	}

	public Style findOne(Integer style) {
		Assert.notNull(style);
		return styleRepository.findOne(style);
	}

	public Style save(Style style) {
		Assert.notNull(style);
		return styleRepository.save(style);
	}
	
	public Style findByCourse(int courseId){
		return styleRepository.styleOfCourse(courseId);
	}

}
