package controllers;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Style;
import services.StyleService;

@Controller
@RequestMapping("/style")
public class StyleController extends AbstractController {

	// Services

	@Autowired
	private StyleService styleService;
	
	// Constructors -----------------------------------------------------------
	public StyleController() {
		super();
	}

	// Actions

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(required=false) Style q) {
		ModelAndView result;
		result = new ModelAndView("style/list");
		if(q==null){
			result.addObject("styles", styleService.findAll());
		}else{
			result.addObject("styles", Arrays.asList(q));
		}
		return result;
	}

	@RequestMapping("/view")
	public ModelAndView view(@RequestParam Style q){
		ModelAndView res;
		
		res = new ModelAndView("style/view");
		res.addObject("style",q);
		
		return res;
	}
	
	@RequestMapping("/administrator/list")
	public ModelAndView listAdministrator() {
		ModelAndView result;

		result = new ModelAndView("style/listAdministrator");
		result.addObject("styles", styleService.findAll());

		return result;
	}
	
	@RequestMapping("/administrator/listPicture")
	public ModelAndView listPictures(HttpServletRequest request,@RequestParam Style q){
		ModelAndView res;
		request.getSession().setAttribute("style_id", q.getId());
		
		res = new ModelAndView("style/listPicture");
		res.addObject("style",q);
		
		return res;
	}
	
	@RequestMapping("/administrator/listVideo")
	public ModelAndView listVideos(HttpServletRequest request,@RequestParam Style q){
		ModelAndView res;
		request.getSession().setAttribute("style_id", q.getId());
		
		res = new ModelAndView("style/listVideo");
		res.addObject("videos",q.getVideos());
		return res;
	}
	
	@RequestMapping("/administrator/addPicture")
	public ModelAndView addPicture(HttpServletRequest request,@RequestParam Style q){
		ModelAndView res;
		request.getSession().setAttribute("style_id", q.getId());
		
		res = new ModelAndView("style/addPicture");
		res.addObject("picture", new String());
		
		return res;
	}
	
	@RequestMapping("/administrator/addVideo")
	public ModelAndView addVideos(HttpServletRequest request,@RequestParam Style q){
		ModelAndView res;
		request.getSession().setAttribute("style_id", q.getId());
		
		res = new ModelAndView("style/addVideo");
		res.addObject("video", new String());
		
		return res;
	}
	
	@RequestMapping(value = "/administrator/addPictureSave", method = RequestMethod.POST)
	public ModelAndView addPictureSave(HttpServletRequest request, @RequestParam String picture){
		ModelAndView res;
		int style_id = (int) request.getSession().getAttribute("style_id");
		
		Style style = styleService.findOne(style_id);
		style.getPictures().add(picture);
		styleService.save(style);
		res = new ModelAndView("redirect:/style/administrator/edit.do?q=" + style_id);
		
		request.getSession().removeAttribute("style_id");
		
		return res;
	}
	
	@RequestMapping("/administrator/addVideoSave")
	public ModelAndView addVideo(HttpServletRequest request, @RequestParam String video){
		ModelAndView res;
		int style_id = (int) request.getSession().getAttribute("style_id");
		
		Style style = styleService.findOne(style_id);
		style.getVideos().add(video);
		styleService.save(style);
		res = new ModelAndView("redirect:/style/administrator/edit.do?q=" + style_id);
		
		request.getSession().removeAttribute("style_id");

		return res;
	}
	
	@RequestMapping(value="/administrator/deleteVideo")
	public ModelAndView deleteVideo(HttpServletRequest request, @RequestParam String video){
		ModelAndView res;
		int style_id = (int) request.getSession().getAttribute("style_id");
		Style style = styleService.findOne(style_id);
		
		style.getVideos().remove(video);
		styleService.save(style);
		res = new ModelAndView("redirect:/style/administrator/edit.do?q=" + style_id);
		
		request.getSession().removeAttribute("style_id");
		return res;
	}
	
	@RequestMapping(value="/administrator/deletePicture")
	public ModelAndView deletePicture(HttpServletRequest request, @RequestParam String picture){
		ModelAndView res;
		int style_id = (int) request.getSession().getAttribute("style_id");
		Style style = styleService.findOne(style_id);
		
		style.getPictures().remove(picture);
		styleService.save(style);
		res = new ModelAndView("redirect:/style/administrator/edit.do?q=" + style_id);
		
		request.getSession().removeAttribute("style_id");
		return res;
	}

	@RequestMapping(value = "/administrator/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		result = createNewModelAndView(styleService.create(), null);

		return result;
	}

	@RequestMapping(value = "/administrator/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam Style q) {
		ModelAndView result;
		
		result = createEditModelAndView(q, null);

		return result;
	}

	@RequestMapping(value = "/administrator/save", method = RequestMethod.POST, params = "save")
	public ModelAndView saveCreate(@Valid Style style, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createNewModelAndView(style, null);
		} else {
			try {
				styleService.save(style);
				result = new ModelAndView("redirect:/style/administrator/list.do");
			} catch (Throwable th) {
				result = createNewModelAndView(style, "style.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/administrator/save-edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid Style style, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(style, null);
		} else {
			try {
				styleService.save(style);
				result = new ModelAndView("redirect:/style/administrator/list.do");
			} catch (Throwable th) {
				result = createEditModelAndView(style, "style.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/administrator/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam Style q) {
		ModelAndView result;
		
		styleService.delete(q);
		result = new ModelAndView("redirect:/style/administrator/list.do");
		
		return result;
	}


	protected ModelAndView createNewModelAndView(Style style, String message) {
		ModelAndView result;
		result = new ModelAndView("style/create");
		result.addObject("style", style);
		result.addObject("message", message);
		return result;
	}

	protected ModelAndView createEditModelAndView(Style style, String message) {
		ModelAndView result = new ModelAndView("style/edit");

		result.addObject("style", style);
		result.addObject("message", message);
		return result;
	}

}