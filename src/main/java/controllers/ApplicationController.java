package controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Application;
import domain.Course;
import domain.Dancer;
import security.LoginService;
import services.ApplicationService;

@Controller
@RequestMapping("/application")
public class ApplicationController extends AbstractController{

	//Services
	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private LoginService loginService;


	// Constructors -----------------------------------------------------------
	public ApplicationController(){
		super();
	}

	//Actions
	
	@RequestMapping(value = "/dancer/mylist", method = RequestMethod.GET)
	public ModelAndView listByDancer() {
		ModelAndView result;
		
		Dancer d = (Dancer) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		result = new ModelAndView("application/mylist");
		result.addObject("applications", d.getApplications());

		return result;
	}


	@RequestMapping(value = "/academy/listByCourse", method = RequestMethod.GET)
	public ModelAndView listByCourse(@RequestParam Course q) {
		ModelAndView result;

		result = new ModelAndView("application/list");
		result.addObject("applications", q.getApplications());
		result.addObject("a", 2);

		return result;
	}


	@RequestMapping("/academy/accept")
	public ModelAndView accept(@RequestParam Application q) {
		ModelAndView result;

		applicationService.accept(q);

		int id = q.getCourse().getId();
		result = new ModelAndView("redirect:/application/academy/listByCourse.do?q=" + id);

		return result;
	}

	@RequestMapping("/academy/denied")
	public ModelAndView denied(@RequestParam Application q) {
		ModelAndView result;
		applicationService.denied(q);

		int id = q.getCourse().getId();
		result = new ModelAndView("redirect:/application/academy/listByCourse.do?q=" + id);

		return result;
	}


	@RequestMapping("/dancer/apply")
	public ModelAndView apply(@RequestParam Course q) {

		ModelAndView result = new ModelAndView("redirect:/course/list.do?a=0");

		Dancer dancer = (Dancer) loginService.findActorByUsername(LoginService.getPrincipal().getId());
		List<Application> apps = dancer.getApplications();
		List<Course> courses = new ArrayList<Course>();
		for(Application app: apps){
			courses.add(app.getCourse());
		}
		if(!courses.contains(q)){
			applicationService.apply(q);
			result = new ModelAndView("redirect:/course/list.do?a=0");
		}

		return result;
	}



}
