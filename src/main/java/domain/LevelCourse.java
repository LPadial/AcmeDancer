
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class LevelCourse {

	//Attributes

	private final String	BEGINNER	= "BEGINNER", INTERMEDIATE = "INTERMEDIATE", ADVANCED = "ADVANCED", PROFESSIONAL = "PROFESSIONAL";
	private String			value;


	//Getters

	@NotBlank
	@Pattern(regexp = "^" + BEGINNER + "|" + INTERMEDIATE + "|" + ADVANCED + "|" + PROFESSIONAL + "$")
	public String getValue() {
		return value;
	}

	//Setters

	public void setValue(String value) {
		this.value = value;
	}

}
