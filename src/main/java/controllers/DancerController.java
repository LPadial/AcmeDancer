package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Dancer;
import services.DancerService;

@Controller
@RequestMapping("/dancer")
public class DancerController extends AbstractController{

	@Autowired
	private DancerService dancerService;
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		
		res = new ModelAndView("dancer/create");
		res.addObject("dancer", dancerService.create());
		
		return res;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Dancer dancer, BindingResult binding){
		ModelAndView res;

		if(binding.hasErrors()){
			res = createModelAndView(dancer, null);
		}else{
			try{
				dancerService.save(dancer);
				res = new ModelAndView("redirect:/welcome/index.do");
			}catch(Throwable e){
				res = createModelAndView(dancer, "dancer.commit.error");
			}
		}
		return res;
	}
	protected ModelAndView createModelAndView(Dancer dancer, String message){
		ModelAndView res;
		
		res=new ModelAndView("dancer/create");
		res.addObject("dancer", dancer);
		res.addObject("message", message);
		
		return res;
	}
}
