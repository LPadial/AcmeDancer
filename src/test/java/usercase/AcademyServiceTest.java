
package usercase;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Academy;
import services.AcademyService;
import services.CourseService;
import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class AcademyServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private AcademyService	academyService;

	@Autowired
	private CourseService	courseService;

	//Templates


	/*
	 * 5.1: An actor who is not authenticated must be able to register as academy.
	 */
	public void academyRegisterTemplate(final String username, final String password, String actorName, String surname, String commercialName, String email, String phone, String postalAddress, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Academy res = academyService.create();

			Assert.notNull(username);
			Assert.notNull(password);
			if (phone != null) {
				Assert.isTrue(phone.matches("(\\+\\d{2} \\(\\d{1,3}\\) \\d{4,})|(\\+\\d{2} \\d{4,})"));
			}
			Assert.notNull(email);
			Assert.notNull(actorName);
			Assert.notNull(surname);
			Assert.notNull(commercialName);

			res.getUserAccount().setUsername(username);
			res.getUserAccount().setPassword(password);
			res.setActorName(actorName);
			res.setSurname(surname);
			res.setCommercialName(commercialName);
			res.setEmail(email);
			res.setPhone(phone);
			res.setAddress(postalAddress);

			academyService.save(res);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * 5.2: Browse the catalogue of academies and navigate to the courses they offer.
	 */
	public void browseAcademiesTemplate(final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Academy a = academyService.findOne(id);
			String s = a.getActorName().toString();
			academyService.findAll();
			courseService.coursesOfAcademy(id);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void academyRegisterDriver() {

		final Object testingData[][] = {

			//Test #01: All parameters correct. Expected true.
			{
				"academyTest", "academyTest", "name", "surname", "commercialName", "email@mail.com", "+34 (8) 3243", "postalAddress", null
			},

			//Test #02: Some fields empty. Expected false.
			{
				"academyTest", "academyTest", null, null, "commercialName", "email@mail.com", "+34 (8) 3243", "postalAddress", IllegalArgumentException.class
			},

			//Test #03: Phone number doesn't match pattern. Expected false.
			{
				"academyTest", "academyTest", "name", "surname", "commercialName", "email@mail.com", "3568356", "postalAddress", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.academyRegisterTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (Class<?>) testingData[i][8]);
	}

	@Test
	public void browseAcademiesDriver() {

		final Object testingData[][] = {

			//Test #01: Correct access. Expected true.
			{
				640, null
			},

			//Test #02: Attempt to access a nonexistent academy. Expected false.
			{
				null, IllegalArgumentException.class
			},

			//Test #03: Attempt to access a nonexistent entity. Expected false.
			{
				1000, NullPointerException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.browseAcademiesTemplate((Integer) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
