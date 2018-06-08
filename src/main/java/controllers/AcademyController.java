package controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Academy;
import services.AcademyService;

@Controller
@RequestMapping("/academy")
public class AcademyController extends AbstractController{


	//Services

	@Autowired
	private AcademyService academyService;

	// Constructors -----------------------------------------------------------
	public AcademyController(){
		super();
	}

	//Actions

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("academy/list");

		result.addObject("academies", academyService.findAll());

		return result;
	}


	@RequestMapping("/view")
	public ModelAndView view(@RequestParam Academy q) {
		ModelAndView result;

		result = new ModelAndView("academy/view");
		result.addObject("academy", q);

		return result;
	}

	@RequestMapping(value="/create",method=RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;

		res = new ModelAndView("academy/create");
		res.addObject("academy",academyService.create());

		return res;
	}

	@RequestMapping(value="/save",method=RequestMethod.POST,params = "save")
	public ModelAndView saveCreate(@Valid Academy academy, BindingResult binding){
		ModelAndView res;

		if(binding.hasErrors()){
			res = createModelAndView(academy,null);
		}else{
			try{
				academyService.save(academy);
				res = new ModelAndView("redirect:/welcome/index.do");

			}catch(Throwable e){
				res = createModelAndView(academy, "academy.commit.error");
			}
		}
		return res;

	}
	protected ModelAndView createModelAndView(Academy academy,String message){
		ModelAndView res;
		res = new ModelAndView("academy/create");
		res.addObject("academy", academy);
		res.addObject("message",message);
		return res;
	}


}
