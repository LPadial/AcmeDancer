package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ChirpService;
import domain.Actor;
import domain.Chirp;

@Controller
@RequestMapping("/chirp/actor")
public class ChirpController extends AbstractController {

	// Services

	@Autowired
	private ChirpService	chirpService;

	@Autowired
	private LoginService	loginService;


	// Constructors -----------------------------------------------------------
	public ChirpController() {
		super();
	}

	// Actions

	@RequestMapping("/mylist")
	public ModelAndView mylist() {
		ModelAndView result;

		Actor actor = loginService.findActorByUsername(LoginService.getPrincipal().getId());
		Collection<Chirp> chirps = chirpService.chirpsOfActor(actor.getId());

		result = new ModelAndView("chirp/list");
		result.addObject("chirps", chirps);
		result.addObject("a", 1);

		return result;
	}

	@RequestMapping("/create")
	public ModelAndView create() {
		ModelAndView result;

		Chirp chirp = chirpService.create();

		result = new ModelAndView("chirp/create");
		result.addObject("chirp", chirp);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Chirp chirp, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("chirp/create");
			result.addObject("chirp", chirp);
		} else {
			try {
				chirpService.save(chirp);
				result = new ModelAndView("redirect:/chirp/actor/mylist.do");
			} catch (Throwable oops) {
				result = new ModelAndView("chirp/create");
				result.addObject("chirp", chirp);
				result.addObject("message", "chirp.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam Chirp q) {
		ModelAndView result;

		Actor a = loginService.findActorByUsername(LoginService.getPrincipal().getId());
		
		if(a != null){
			if(a.getChirps().contains(q)){
				chirpService.delete(q);
				result = new ModelAndView("redirect:/chirp/actor/mylist.do");
			}else{
				result = list();
			}
		}else{
			return list();
		}

		return result;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Chirp> chirps = chirpService.allChirpsOrderByMommentDesc();
		
		Actor actor = loginService.findActorByUsername(LoginService.getPrincipal().getId());

		result = new ModelAndView("chirp/list");
		result.addObject("chirps", chirps);
		result.addObject("followers", actor.getFollower());
		result.addObject("a", 2);

		return result;
	}

	@RequestMapping("/myListSubscribe")
	public ModelAndView listChirpSuscribe() {
		ModelAndView result;

		Integer actorId = loginService.findActorByUsername(LoginService.getPrincipal().getId()).getId();
		Collection<Chirp> chirps = chirpService.listChirpByFollower(actorId);

		result = new ModelAndView("chirp/list");
		result.addObject("chirps", chirps);
		result.addObject("a", 3);

		return result;
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public ModelAndView subscribe(@RequestParam Chirp q) {
		ModelAndView result;

		Actor actor = loginService.findActorByUsername(LoginService.getPrincipal().getId());
		if ((!actor.getFollower().contains(q.getActor())) && q.getActor()!= actor) {
			chirpService.suscribe(q.getActor());

		}
		result = new ModelAndView("redirect:/chirp/actor/myListSubscribe.do");
		return result;
	}

}