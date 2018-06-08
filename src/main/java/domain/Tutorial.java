
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Tutorial extends DomainEntity {

	private String	title;
	private String	description;
	private String	video;
	private Integer	numShows;


	//Getters

	@NotBlank
	public String getTitle() {
		return title;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	@NotBlank
	@URL
	public String getVideo() {
		return video;
	}
	
	public Integer getNumShows() {
		return numShows;
	}

	//Setters
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public void setNumShows(Integer numShows) {
		this.numShows = numShows;
	}

}
