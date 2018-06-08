
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Chirp extends DomainEntity {

	private Date	momentWritten;
	private String	text;
	private Actor	actor;

	//Getters


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	public Date getMomentWritten() {
		return momentWritten;
	}

	@NotBlank
	@Length(min = 0, max = 140)
	public String getText() {
		return text;
	}

	@NotNull
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}

	//Setters
	public void setMomentWritten(Date momentWritten) {
		this.momentWritten = momentWritten;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
