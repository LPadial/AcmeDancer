
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Course extends DomainEntity {

	private String				title;
	private LevelCourse			levelCourse;
	private Date					start;
	private Date					end;
	private String				dayWeek;
	private Date					time;
	private Academy				academy;
	private List<Application>	applications;
	private Style				style;


	//Getters
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	@Valid
	@NotNull
	public LevelCourse getLevelCourse() {
		return levelCourse;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getStart() {
		return start;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEnd() {
		return end;
	}

	@NotBlank
	public String getDayWeek() {
		return dayWeek;
	}

	@NotNull
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	public Date getTime() {
		return time;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Academy getAcademy() {
		return academy;
	}

	@NotNull
	@OneToMany
	public List<Application> getApplications() {
		return applications;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Style getStyle() {
		return style;
	}

	//Setters

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLevelCourse(LevelCourse levelCourse) {
		this.levelCourse = levelCourse;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setDayWeek(String dayWeek) {
		this.dayWeek = dayWeek;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

}
