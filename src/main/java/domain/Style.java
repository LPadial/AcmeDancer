
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Style extends DomainEntity {

	private String			name;
	private String			description;
	private List<String>	pictures;
	private List<String>	videos;
	private List<Course>	courses;


	//Getters
	@NotBlank
	public String getName() {
		return name;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	@NotNull
	@ElementCollection(targetClass = String.class)
	public List<String> getPictures() {
		return pictures;
	}

	@NotNull
	@ElementCollection(targetClass = String.class)
	public List<String> getVideos() {
		return videos;
	}

	@NotNull
	@OneToMany
	public List<Course> getCourses() {
		return courses;
	}

	//Setters

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}

	public void setVideos(List<String> videos) {
		this.videos = videos;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
