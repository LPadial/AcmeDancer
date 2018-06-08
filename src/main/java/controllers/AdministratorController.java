/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import repositories.AdministratorRepository;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	//Service
	
	@Autowired
	private AdministratorRepository administratorRepository;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView result;

		result = new ModelAndView("administrator/dashboard");

		result.addObject("course", administratorRepository.minAvgSdMaxCoursesPerAcademy());
		result.addObject("app", administratorRepository.minAvgSdMaxApplicationsPerCourse());
		result.addObject("tutorialAcad", administratorRepository.minAvgMaxTutorialsPerAcademy());
		result.addObject("tutorialShow", administratorRepository.minAvgMaxTutorialNumShows());
		result.addObject("tutorialSee", administratorRepository.tutorialsOrderByNumShowsDes());
		result.addObject("chirpActor", administratorRepository.avgChirpsPerActor());
		result.addObject("chirpSubcription", administratorRepository.avgSubscriptionPerActor());

		return result;
	}

}
