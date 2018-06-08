
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AcademyRepository;
import repositories.TutorialRepository;
import security.LoginService;
import domain.Academy;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	//Repositories
	@Autowired
	private TutorialRepository	tutorialRepository;
	
	@Autowired
	private AcademyRepository	academyRepository; 
	
	@Autowired
	private LoginService		loginService;


	
	//Services


	//Constructor

	public TutorialService() {
		super();
	}

	//CRUD Methods
	public Tutorial create() {
		Tutorial tutorial = new Tutorial();
		tutorial.setDescription(new String());
		tutorial.setNumShows(0);
		tutorial.setTitle(new String());
		tutorial.setVideo(new String());

		return tutorial;
	}

	public void delete(Tutorial tutorial) {
		Assert.notNull(tutorial);
		List<Academy> academies = academyRepository.findAll();
		for(Academy ac: academies){
			if(ac.getTutorials().contains(tutorial)){
				ac.getTutorials().remove(tutorial);
				academyRepository.save(ac);
			}
		}
		tutorialRepository.delete(tutorial.getId());
	}

	public List<Tutorial> findAll() {
		return tutorialRepository.findAll();
	}

	public Tutorial findOne(Integer tutorial) {
		Assert.notNull(tutorial);
		return tutorialRepository.findOne(tutorial);
	}

	public boolean exists(Integer id) {
		return tutorialRepository.exists(id);
	}
	public Tutorial save(Tutorial arg0) {
		Assert.notNull(arg0);
		Tutorial tutorial = new Tutorial();

		if (exists(arg0.getId())) {
			tutorial = tutorialRepository.findOne(arg0.getId());
			tutorial.setTitle(arg0.getTitle());
			tutorial.setDescription(arg0.getDescription());
			tutorial.setVideo(arg0.getVideo());
			tutorial.setNumShows(arg0.getNumShows());
			return tutorialRepository.save(tutorial);
		} else {
			tutorial = tutorialRepository.save(arg0);
			Academy man = (Academy) loginService.findActorByUsername(LoginService.getPrincipal().getId());
			List<Tutorial> tutorials = man.getTutorials();
			tutorials.add(tutorial);
			man.setTutorials(tutorials);
			academyRepository.save(man);

		}
		return tutorial;
	}

	//Other methods
	public Collection<Tutorial> tutorialsOfAcademy(int academyID) {
		return tutorialRepository.tutorialsOfAcademy(academyID);
	}

}
