
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class StatusApplication {

	//Attributes

	private final String	PENDING	= "PENDING", ACCEPTED = "ACCEPTED", REJECTED = "REJECTED";
	private String			value;


	//Getters

	@NotBlank
	@Pattern(regexp = "^" + PENDING + "|" + ACCEPTED + "|" + REJECTED + "$")
	public String getValue() {
		return value;
	}

	//Setters

	public void setValue(String value) {
		this.value = value;
	}

}
