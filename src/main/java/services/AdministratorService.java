
package services;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Tutorial;
import repositories.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

	// Repository

	@Autowired
	private AdministratorRepository administratorRepository;

	// Service

	// Constructor
	public AdministratorService() {
		super();
	}

	// CRUD Methods
	public Administrator save(Administrator administrator) {
		Assert.notNull(administrator);
		Administrator adm = null;

		if (exists(administrator.getId())) {
			adm = findOne(administrator.getId());

			adm.setActorName(administrator.getActorName());
			adm.setSurname(administrator.getSurname());
			adm.setEmail(administrator.getEmail());
			adm.setPhone(administrator.getPhone());
			adm.setAddress(administrator.getAddress());
			adm.setChirps(administrator.getChirps());
			adm.setFollower(administrator.getFollower());

			adm = administratorRepository.save(adm);
		} else {
			adm = administratorRepository.save(administrator);
		}
		return adm;
	}

	public boolean exists(Integer administratorID) {
		return administratorRepository.exists(administratorID);
	}

	// Other Methods

	public Object[] minAvgSdMaxCoursesPerAcademy() {
		return administratorRepository.minAvgSdMaxCoursesPerAcademy();
	}

	public Object[] minAvgSdMaxApplicationsPerCourse() {
		return administratorRepository.minAvgSdMaxApplicationsPerCourse();
	}

	public Object[] minAvgMaxTutorialsPerAcademy() {
		return administratorRepository.minAvgMaxTutorialsPerAcademy();
	}

	public Object[] minAvgMaxTutorialNumShows() {
		return administratorRepository.minAvgMaxTutorialNumShows();
	}

	public List<Array[]> avgChirpsPerActor() {
		return administratorRepository.avgChirpsPerActor();
	}

	public List<Array[]> avgSubscriptionPerActor() {
		return administratorRepository.avgSubscriptionPerActor();
	}

	public List<Tutorial> tutorialsOrderByNumShowsDes() {
		return administratorRepository.tutorialsOrderByNumShowsDes();
	}

	public Administrator findOne(Integer adminID) {
		return administratorRepository.findOne(adminID);
	}

}

