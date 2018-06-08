package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Academy;
import domain.Actor;
import domain.Administrator;
import domain.Dancer;
import security.LoginService;
import services.AcademyService;
import services.AdministratorService;
import services.DancerService;


@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController{
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private DancerService dancerService;
	@Autowired
	private AcademyService academyService;
	
	public ActorController(){
		super();
	}
	
	@RequestMapping(value="/edit", method= RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView result;
		Actor actor;
		
		actor = loginService.findActorByUsername(LoginService.getPrincipal().getUsername());
		
		result = new ModelAndView("actor/edit");
		result.addObject("person", actor);
		result.addObject("administrator", actor);
		result.addObject("dancer", actor);
		result.addObject("academy", actor);
		
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Actor actor){
		ModelAndView result;
		
		result=createEditModelAndView(actor,null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Actor actor,String message){
		ModelAndView result;
		
		result = new ModelAndView("actor/edit");
		result.addObject("person", actor);
		result.addObject("administrator", actor);
		result.addObject("dancer", actor);
		result.addObject("academy", actor);
		
		return result;
	}
	
	@RequestMapping(value = "/save-administrator", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAdministrator(@Valid Administrator administrator, BindingResult binding) {
		ModelAndView result;
		
		for(FieldError e: binding.getFieldErrors()){
			System.out.println(e.getDefaultMessage());
		}
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(administrator);
		} else {
			try {
				administratorService.save(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(administrator, "actor.commit.error"); } }

		return result; }
	
	@RequestMapping(value="/save-academy", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAcademy(@Valid Academy academy, BindingResult binding){
		ModelAndView result;
		
		for(FieldError e: binding.getFieldErrors()){
			System.out.println(e.getDefaultMessage());
		}
		
		if(binding.hasErrors()){
			result = createEditModelAndView(academy);
		}else{
			try{
				academyService.save(academy);
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch(Throwable e){
				result = createEditModelAndView(academy, "actor.commit.error");
			}
		}
		return result;
	}
	
	@RequestMapping(value="/save-dancer", method = RequestMethod.POST, params = "save")
	public ModelAndView saveDancer(@Valid Dancer dancer, BindingResult binding){
		ModelAndView result;
		
		for(FieldError e: binding.getFieldErrors()){
			System.out.println(e.getDefaultMessage());
		}
		
		if(binding.hasErrors()){
			result = createEditModelAndView(dancer);
		}else{
			try{
				dancerService.save(dancer);
				result = new ModelAndView("redirect:/welcome/index.do");
			}catch(Throwable e){
				result = createEditModelAndView(dancer, "actor.commit.error");
			}
		}
		return result;
	}
}
