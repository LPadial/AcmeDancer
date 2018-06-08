
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Application;
import domain.Chirp;
import domain.Dancer;
import repositories.DancerRepository;
import security.Authority;
import security.UserAccount;

@Service
@Transactional
public class DancerService {

	//Repositories
	@Autowired
	private DancerRepository dancerRepository;

	//Services


	//Constructor
	public DancerService() {
		super();
	}

	//CRUD Methods
	public Dancer create() {
		Dancer dancer = new Dancer();
		dancer.setActorName(new String());
		dancer.setAddress(new String());
		dancer.setEmail(new String());
		dancer.setFollower(new ArrayList<Actor>());
		dancer.setPhone(new String());
		dancer.setSurname(new String());
		dancer.setChirps(new ArrayList<Chirp>());
		dancer.setApplications(new ArrayList<Application>());

		Authority a = new Authority();
		a.setAuthority(Authority.DANCER);
		UserAccount account = new UserAccount();
		account.setAuthorities(Arrays.asList(a));
		dancer.setUserAccount(account);

		return dancer;

	}
	public Dancer findOne(Integer dancer) {
		Assert.notNull(dancer);
		return dancerRepository.findOne(dancer);
	}

	public Dancer save(Dancer dancer) {
		Assert.notNull(dancer);
		Dancer dan = null;

		if (exists(dancer.getId())) {
			dan = findOne(dancer.getId());

			dan.setActorName(dancer.getActorName());
			dan.setAddress(dancer.getAddress());
			dan.setEmail(dancer.getEmail());
			dan.setPhone(dancer.getPhone());
			dan.setSurname(dancer.getSurname());
			dan.setApplications(dancer.getApplications());
			dan.setChirps(dancer.getChirps());
			dan.setFollower(dancer.getFollower());

			dan = dancerRepository.save(dan);
		} else {

			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			dancer.getUserAccount().setPassword(encoder.encodePassword(dancer.getUserAccount().getPassword(), null));

			dan = dancerRepository.save(dancer);
		}
		return dan;
	}

	public boolean exists(Integer dancerId) {
		return dancerRepository.exists(dancerId);
	}

	public List<Dancer> findAll() {
		return dancerRepository.findAll();
	}

	//Other methods
	public Collection<Application> applicationsOfDancer(int DancerID) {
		return dancerRepository.applicationsOfDancer(DancerID);
	}

}
