
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	private Date				createMoment;
	private StatusApplication	statusApplication;
	private Course				course;


	//getters

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getCreateMoment() {
		return createMoment;
	}
	
	@Valid
	@NotNull
	public StatusApplication getStatusApplication() {
		return statusApplication;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Course getCourse() {
		return course;
	}

	//setters
	public void setCreateMoment(Date createMoment) {
		this.createMoment = createMoment;
	}

	public void setStatusApplication(StatusApplication statusApplication) {
		this.statusApplication = statusApplication;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
