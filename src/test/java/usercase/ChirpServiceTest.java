
package usercase;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Chirp;
import security.LoginService;
import services.AcademyService;
import services.ChirpService;
import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class ChirpServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private ChirpService	chirpService;

	@Autowired
	private LoginService	loginService;

	@Autowired
	private AcademyService	academyService;

	//Templates


	/*
	 * 22.1: An authenticated actor must be able to manage their chirps, which includes listing, writing and deleting them.
	 */
	public void manageChirpTemplate(final String username, final Integer id, String text, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			//Listing
			Assert.notNull(username);
			chirpService.chirpsOfActor(id);

			//Creating
			Chirp res = chirpService.create();
			Assert.notNull(text);
			res.setText(text);
			chirpService.save(res);

			//Deleting
			chirpService.delete(res);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * 22.2: An authenticated actor can subscribe to the chirps of other actors.
	 */
	public void subscribeChirpTemplate(final String username, final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Assert.notNull(username);
			Actor actor = academyService.findOne(id);
			chirpService.suscribe(actor);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void manageChirpDriver() {

		final Object testingData[][] = {

			//Test #01: Correct access. Expected true.
			{
				"academy3", 642, "text", null
			},

			//Test #02: Access by anonymous user. Expected false.
			{
				null, 642, "text", IllegalArgumentException.class
			},

			//Test #03: Attempt to publish an empty chirp. Expected false.
			{
				"academy3", 642, null, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.manageChirpTemplate((String) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);
	}

	@Test
	public void subscribeChirpDriver() {

		final Object testingData[][] = {

			//Test #01: Correct access. Expected true.
			{
				"academy1", 640, null
			},

			//Test #02: Attempt to access as anonymous user. Expected false.
			{
				null, 640, IllegalArgumentException.class
			},

			//Test #03: Attempt to access nonexistant actor. Expected false.
			{
				"academy1", null, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.subscribeChirpTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
