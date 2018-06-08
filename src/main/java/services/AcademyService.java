
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Academy;
import domain.Actor;
import domain.Chirp;
import domain.Course;
import domain.Tutorial;
import repositories.AcademyRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class AcademyService {

	//Repositories
	@Autowired
	private AcademyRepository academyRepository;

	//Services


	//Constructor
	public AcademyService() {
		super();
	}

	//CRUD Methods

	public Academy create() {
		Academy academy = new Academy();
		academy.setActorName(new String());
		academy.setAddress(new String());
		academy.setEmail(new String());
		academy.setFollower(new ArrayList<Actor>());
		academy.setPhone(new String());
		academy.setSurname(new String());
		academy.setChirps(new ArrayList<Chirp>());

		academy.setCommercialName(new String());
		academy.setTutorials(new ArrayList<Tutorial>());
		academy.setCourses(new ArrayList<Course>());

		Authority a = new Authority();
		a.setAuthority(Authority.ACADEMY);
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		academy.setUserAccount(account);

		return academy;

	}

	public List<Academy> findAll() {
		return academyRepository.findAll();
	}

	public Academy findOne(Integer academy) {
		Assert.notNull(academy);
		return academyRepository.findOne(academy);
	}

	public Academy save(Academy academy) {
		Assert.notNull(academy);
		Academy aca = null;

		if (exists(academy.getId())) {
			aca = findOne(academy.getId());

			aca.setActorName(academy.getActorName());
			aca.setSurname(academy.getSurname());
			aca.setEmail(academy.getEmail());
			aca.setPhone(academy.getPhone());
			aca.setAddress(academy.getAddress());
			aca.setChirps(academy.getChirps());
			aca.setCommercialName(academy.getCommercialName());
			aca.setCourses(academy.getCourses());
			aca.setFollower(academy.getFollower());
			aca.setTutorials(academy.getTutorials());

			aca = academyRepository.save(aca);
		} else {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			academy.getUserAccount().setPassword(encoder.encodePassword(academy.getUserAccount().getPassword(), null));

			aca = academyRepository.save(academy);
		}
		return aca;
	}

	public boolean exists(Integer academyID) {
		return academyRepository.exists(academyID);
	}

	//Others Methods

	public Academy academyOfCourse(int CourseID) {
		return academyRepository.academyOfCourse(CourseID);
	}

}
