package controllers;

import java.util.ArrayList;
import java.util.Collection;
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
import domain.Tutorial;
import security.LoginService;
import services.TutorialService;

@Controller
@RequestMapping("/tutorial")
public class TutorialController extends AbstractController {

	// Services

	@Autowired
	private TutorialService tutorialService;

	@Autowired
	private LoginService loginService;

	// Constructors -----------------------------------------------------------
	public TutorialController() {
		super();
	}

	// Actions

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		result = new ModelAndView("tutorial/list");

		result.addObject("a", 0);
		result.addObject("tutorials", tutorialService.findAll());

		return result;
	}

	@RequestMapping("/academy/mylist")
	public ModelAndView myList() {
		ModelAndView res;
		Academy academy = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());

		res = new ModelAndView("tutorial/list");
		res.addObject("tutorials", academy.getTutorials());
		res.addObject("a", 2);

		return res;
	}

	@RequestMapping("/listByAcademy")
	public ModelAndView listByAcademy(@RequestParam Academy q) {
		ModelAndView result;

		Collection<Tutorial> tutorials = new ArrayList<Tutorial>();
		tutorials = tutorialService.tutorialsOfAcademy(q.getId());
		result = new ModelAndView("tutorial/list");
		result.addObject("tutorials", tutorials);
		result.addObject("a", 1);

		return result;
	}

	@RequestMapping("/listByMyAcademy")
	public ModelAndView listByMyAcademy() {
		ModelAndView result;
		Academy academy = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());

		List<Tutorial> tutorials = new ArrayList<Tutorial>();

		Academy a = (Academy) loginService.findActorByUsername(academy.getId());
		tutorials.addAll(tutorialService.tutorialsOfAcademy(a.getId()));

		result = new ModelAndView("tutorial/list");
		result.addObject("tutorials", tutorials);
		result.addObject("a", 2);

		return result;
	}

	@RequestMapping("/view")
	public ModelAndView view(@RequestParam Tutorial q) {
		ModelAndView result;
		result = new ModelAndView("tutorial/view");
		Integer num = q.getNumShows() + 1;
		q.setNumShows(num);
		tutorialService.save(q);
		result.addObject("tutorial", q);
		return result;
	}

	@RequestMapping(value = "/academy/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(tutorialService.create(), null);

		return result;
	}

	@RequestMapping(value = "/academy/save-create", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Tutorial tutorial, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(tutorial, null);
		} else {
			try {
				tutorialService.save(tutorial);
				result = new ModelAndView("redirect:/tutorial/academy/mylist.do");

			} catch (Throwable th) {
				result = createNewModelAndView(tutorial, "tutorial.commit.error");
			}
		}
		return result;
	}

	protected ModelAndView createNewModelAndView(Tutorial tutorial, String message) {
		ModelAndView result;
		result = new ModelAndView("tutorial/create");
		result.addObject("tutorial", tutorial);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam Tutorial q) {
		ModelAndView result;

		Academy a = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());

		if (a != null) {
			if (a.getTutorials().contains(q)) {
				result = new ModelAndView("tutorial/edit");
				result.addObject("tutorial", q);
				result.addObject("message", null);

			} else {
				result = list();
			}
		} else {
			return list();
		}

		return result;
	}

	@RequestMapping(value = "/academy/save-edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Tutorial tutorial, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("tutorial/edit");
			result.addObject("tutorial", tutorial);
			result.addObject("message", null);
		} else {
			try {
				tutorialService.save(tutorial);
				result = new ModelAndView("redirect:/tutorial/academy/mylist.do");
			} catch (Throwable oops) {
				result = new ModelAndView("tutorial/edit");
				result.addObject("tutorial", tutorial);
				result.addObject("message", "tutorial.commit.error");
			}
		}

		return result;
	}

	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam Tutorial q) {
		ModelAndView result;

		Academy a = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());

		if (a != null) {
			if (a.getTutorials().contains(q)) {
				try {
					tutorialService.delete(q);
					result = new ModelAndView("redirect:/tutorial/academy/mylist.do");
				} catch (Throwable e) {
					result = new ModelAndView("redirect:/tutorial/academy/mylist.do");
				}
			} else {
				result = list();
			}
		} else {
			return list();
		}
		return result;

	}
}