
package usercase;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Dancer;
import services.DancerService;
import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class DancerServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private DancerService dancerService;

	//Templates


	/*
	 * 5.1: An actor who is not authenticated must be able to register as dancer.
	 */
	public void dancerRegisterTemplate(final String username, final String password, String actorName, String surname, String email, String phone, String postalAddress, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Dancer res = dancerService.create();

			Assert.notNull(username);
			Assert.notNull(password);
			if (phone != null) {
				Assert.isTrue(phone.matches("(\\+\\d{2} \\(\\d{1,3}\\) \\d{4,})|(\\+\\d{2} \\d{4,})"));
			}
			Assert.notNull(email);
			Assert.notNull(actorName);
			Assert.notNull(surname);

			res.getUserAccount().setUsername(username);
			res.getUserAccount().setPassword(password);
			res.setActorName(actorName);
			res.setSurname(surname);
			res.setEmail(email);
			res.setPhone(phone);
			res.setAddress(postalAddress);

			dancerService.save(res);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * 6.2: An authenticated actor may edit his or her personal data.
	 */
	public void editDataTemplate(final String username, final Integer id, String actorName, String surname, String email, String phone, String postalAddress, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Dancer res = dancerService.findOne(id);

			if (phone != null) {
				Assert.isTrue(phone.matches("(\\+\\d{2} \\(\\d{1,3}\\) \\d{4,})|(\\+\\d{2} \\d{4,})"));
			}
			Assert.notNull(email);
			Assert.notNull(actorName);
			Assert.notNull(surname);

			res.setActorName(actorName);
			res.setSurname(surname);
			res.setEmail(email);
			res.setPhone(phone);
			res.setAddress(postalAddress);

			dancerService.save(res);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void dancerRegisterDriver() {

		final Object testingData[][] = {

			//Test #01: All parameters correct. Expected true.
			{
				"dancerTest", "dancerTest", "name", "surname", "email@mail.com", "+34 (8) 3243", "postalAddress", null
			},

			//Test #02: Some fields empty. Expected false.
			{
				"dancerTest", "dancerTest", null, null, "email@mail.com", "+34 (8) 3243", "postalAddress", IllegalArgumentException.class
			},

			//Test #03: Phone number doesn't match pattern. Expected false.
			{
				"dancerTest", "dancerTest", "name", "surname", "email@mail.com", "3568356", "postalAddress", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.dancerRegisterTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(Class<?>) testingData[i][7]);
	}

	@Test
	public void editDataDriver() {

		final Object testingData[][] = {

			//Test #01: All parameters correct. Expected true.
			{
				"dancer1", 643, "newName", "newSurname", "newmail@mail.com", "+34 (8) 3243", "newAddress", null
			},

			//Test #02: All fields empty. Expected false.
			{
				"dancer1", 643, null, null, null, null, null, IllegalArgumentException.class
			},

			//Test #03: Phone number doesn't match pattern. Expected false.
			{
				"dancer1", 643, "newName", "newSurname", "newmail@mail.com", "24556824", "newAddress", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.editDataTemplate((String) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(Class<?>) testingData[i][7]);
	}
}
