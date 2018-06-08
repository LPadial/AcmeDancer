
package controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Academy;
import domain.Application;
import domain.Course;
import domain.Dancer;
import domain.Style;
import security.LoginService;
import services.CourseService;
import services.StyleService;

@Controller
@RequestMapping("/course")
public class CourseController extends AbstractController {

	//Services

	@Autowired
	private CourseService	courseService;

	@Autowired
	private LoginService	loginService;

	@Autowired
	private StyleService	styleService;


	// Constructors -----------------------------------------------------------
	public CourseController() {
		super();
	}

	//Actions

	@RequestMapping(value = "/listNoRegister", method = RequestMethod.GET)
	public ModelAndView listNoRegister() {
		ModelAndView result;

		result = new ModelAndView("course/list");

		result.addObject("courses", courseService.findAll());
		result.addObject("a", 0);

		return result;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("course/list");

		if (LoginService.hasRole("DANCER")) {
			Dancer actor = (Dancer) loginService.findActorByUsername(LoginService.getPrincipal().getId());
			List<Course> coursesApplyActor = new ArrayList<Course>();
			for (Application app : actor.getApplications()) {
				coursesApplyActor.add(app.getCourse());
			}
			result.addObject("coursesApplyActor", coursesApplyActor);
		}

		result.addObject("a", 0);
		result.addObject("courses", courseService.findAll());

		return result;
	}

	@RequestMapping("/listByAcademy")
	public ModelAndView listByAcademy(@RequestParam Academy q) {
		ModelAndView result;

		result = new ModelAndView("course/list");
		result.addObject("courses", q.getCourses());
		result.addObject("a", 1);

		return result;
	}

	@RequestMapping("/listByStyle")
	public ModelAndView listByStyle(@RequestParam Style q) {
		ModelAndView result;

		result = new ModelAndView("course/list");
		result.addObject("courses", q.getCourses());
		result.addObject("a", 2);

		return result;
	}

	@RequestMapping("/academy/mylist")
	public ModelAndView listByActor() {
		ModelAndView result;

		Academy a = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());

		result = new ModelAndView("course/mylist");
		result.addObject("courses", a.getCourses());

		return result;
	}

	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(required = false) String q) {
		ModelAndView result;
		result = new ModelAndView("course/list");
		result.addObject("courses", courseService.findCourses(q));
		result.addObject("a", 0);
		return result;
	}

	@RequestMapping(value = "/academy/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(courseService.create(), null);

		return result;
	}

	@RequestMapping("/academy/edit")
	public ModelAndView edit(@RequestParam Course q) {
		ModelAndView result;
		Academy a = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		
		if(a != null){
			if(a.getCourses().contains(q)){
				result = createNewModelAndView(q, null);
			}else{
				result = list();
			}
		}else{
			return list();
		}
		

		return result;
	}

	@RequestMapping(value = "/academy/save-create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreateEdit(@Valid Course course, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(course, null);
		} else {
			try {
				courseService.save(course);
				result = new ModelAndView("redirect:/course/academy/mylist.do");

			} catch (Throwable th) {
				result = createNewModelAndView(course, "course.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Course course, String message) {
		ModelAndView result;

		if (course.getId() == 0) {
			result = new ModelAndView("course/create");
		} else {
			result = new ModelAndView("course/edit");
		}
		result.addObject("course", course);
		result.addObject("message", message);
		result.addObject("styles", styleService.findAll());
		return result;
	}

	@RequestMapping("/academy/delete")
	public ModelAndView delete(@RequestParam Course q) {
		ModelAndView result;

		Academy a = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		
		if(a != null){
			if(a.getCourses().contains(q)){
				courseService.delete(q);
				result = new ModelAndView("redirect:/course/academy/mylist.do");
			}else{
				result = list();
			}
		}else{
			return list();
		}

		return result;
	}
}
