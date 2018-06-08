package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Actor;
import services.AcademyService;
import services.AdministratorService;
import services.DancerService;

@Component
@Transactional
public class StringToActorConverter implements Converter<String, Actor> {

	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private DancerService dancerService;
	@Autowired
	private AcademyService academyService;

	@Override
	public Actor convert(String actor) {
		int id;

		try {
			id = Integer.valueOf(actor.trim());
		} catch (NumberFormatException e) {
			return null;
		}

		if (administratorService.exists(id)) {
			return administratorService.findOne(id);
		}

		if (dancerService.exists(id)) {
			return dancerService.findOne(id);
		}

		return academyService.findOne(id);

	}

}
