
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CourseRepository;
import security.LoginService;
import domain.Academy;
import domain.Application;
import domain.Course;
import domain.LevelCourse;
import domain.Style;

@Service
@Transactional
public class CourseService {

	//Repositories
	@Autowired
	private CourseRepository	courseRepository;

	//Services
	@Autowired
	private StyleService		styleService;

	@Autowired
	private AcademyService		academyService;
	
	@Autowired
	private ApplicationService	applicationService;
	
	@Autowired
	private LoginService		loginService;

	//Constructor

	public CourseService() {
		super();
	}

	//CRUD Methods

	public Course create() {

		Course course = new Course();
		course.setTitle(new String());
		course.setLevelCourse(new LevelCourse());
		course.setStart(new Date());
		course.setEnd(new Date());
		course.setDayWeek(new String());
		course.setTime(new Date());
		Academy a = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		course.setAcademy(a);
		course.setApplications(new ArrayList<Application>());
		course.setStyle(new Style());

		return course;
	}

	public void delete(Course course) {
		Assert.notNull(course);

		Style style = course.getStyle();
		Academy academy = course.getAcademy();

		if(!course.getApplications().isEmpty()){
			List<Application> apps = course.getApplications();
			List<Application> newApps = new ArrayList<Application>();
			course.setApplications(newApps);

			for(Application app : apps){
				applicationService.delete(app.getId());
			}
		}

		List<Course> coursesOfStyle = style.getCourses();
		List<Course> coursesOfAcademy  = academy.getCourses();

		coursesOfStyle.remove(course);
		coursesOfAcademy.remove(course);

		style.setCourses(coursesOfStyle);
		academy.setCourses(coursesOfAcademy);

		styleService.save(style);
		academyService.save(academy);

		courseRepository.delete(course);
	}


	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Course findOne(Integer course) {
		Assert.notNull(course);
		return courseRepository.findOne(course);
	}

	public Course save(Course arg0) {

		  Assert.notNull(arg0);

		  Course course = new Course();

		  if (exists(arg0.getId())) {
		   course = courseRepository.findOne(arg0.getId());
		   course.setTitle(arg0.getTitle());
		   course.setLevelCourse(arg0.getLevelCourse());
		   course.setStart(arg0.getStart());
		   course.setEnd(arg0.getEnd());
		   course.setDayWeek(arg0.getDayWeek());
		   course.setTime(arg0.getTime());
		   course.setStyle(arg0.getStyle());

		   course = courseRepository.save(course);

		   Style style = course.getStyle();

		   List<Style> styles = styleService.findAll();

		   for(Style s: styles){
		    if(!s.equals(style) && s.getCourses().contains(course)){
		     List<Course> courses = s.getCourses();
		     courses.remove(course);
		     s.setCourses(courses);
		    }
		    styleService.save(s);
		   }

		   List<Course> courses1 = style.getCourses();
		   courses1.remove(arg0);
		   courses1.add(course);
		   style.setCourses(courses1);
		   styleService.save(style);

		   return course;

		  } else {
		   course = courseRepository.save(arg0);
		   Academy a = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		   List<Course> coursesA = a.getCourses();
		   coursesA.add(course);
		   a.setCourses(coursesA);
		   academyService.save(a);

		   Style s = course.getStyle();
		   List<Course> coursesS = s.getCourses();
		   coursesS.add(course);
		   s.setCourses(coursesS);
		   styleService.save(s);
		   return courseRepository.save(course);
		  }
	}

	private boolean exists(int id) {
		return courseRepository.exists(id);
	}

	//Other Methods
	public Collection<Course> findCourses(String keyWord) {
		keyWord = keyWord.toLowerCase();
		Collection<Course> courses = courseRepository.findAll();
		Collection<Course> res = new ArrayList<Course>();
		for (Course c : courses) {
			if (c.getTitle().toLowerCase().contains(keyWord) || c.getStyle().getName().toLowerCase().contains(keyWord) || c.getStyle().getDescription().toLowerCase().contains(keyWord)) {
				res.add(c);
			}
		}

		return res;
	}
	public Collection<Course> coursesOfAcademy(int academyID) {
		return courseRepository.coursesOfAcademy(academyID);
	}

	public Collection<Course> coursesOfStyle(int StyleID) {
		return courseRepository.coursesOfStyle(StyleID);
	}


}
