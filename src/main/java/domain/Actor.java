
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	private String		actorName;
	private String		surname;
	private String		email;
	private String		phone;
	private String		address;
	private UserAccount	userAccount;
	private List<Actor>	follower;
	private List<Chirp>	chirps;


	//Geters

	@NotBlank
	public String getActorName() {
		return actorName;
	}
	@NotBlank
	public String getSurname() {
		return surname;
	}

	@Email
	@NotBlank
	public String getEmail() {
		return email;
	}

	@NotNull
	@Pattern(regexp = "^$|^\\+([1-9][0-9]{0,2}) (\\([1-9][0-9]{0,2}\\)) ([a-zA-Z0-9 -]{4,})$")
	public String getPhone() {
		return phone;
	}

	@NotNull
	public String getAddress() {
		return address;
	}
	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return userAccount;
	}

	@NotNull
	@ManyToMany
	public List<Actor> getFollower() {
		return follower;
	}

	@NotNull
	@OneToMany
	public List<Chirp> getChirps() {
		return chirps;
	}

	//Setters
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public void setFollower(List<Actor> follower) {
		this.follower = follower;
	}

	public void setChirps(List<Chirp> chirps) {
		this.chirps = chirps;
	}

}
