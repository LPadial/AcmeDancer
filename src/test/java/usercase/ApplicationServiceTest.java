
package usercase;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Application;
import services.ApplicationService;
import services.DancerService;
import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class ApplicationServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private DancerService		dancerService;

	//Templates


	/*
	 * 7.2: An academy must be able to accept or reject a dancer's application to one of his or her courses.
	 */
	public void acceptRejectApplicationTemplate(final String username, final Integer academyID, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Assert.isTrue(username == "academy1" || username == "academy2" || username == "academy3");
			Integer i = 1;
			Collection<Application> c = applicationService.applicationsPendingOfAcademy(academyID);
			for (Application a : c) {
				if (i % 2 == 0) {
					applicationService.accept(a);
					i++;
				} else {
					applicationService.denied(a);
					i++;
				}
			}

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * 8.2: A dancer must be able to list his or her applications.
	 */
	public void listApplicationTemplate(final String username, final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Assert.isTrue(username == "dancer1" || username == "dancer2" || username == "dancer3");
			dancerService.applicationsOfDancer(id);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void acceptRejectApplicationDriver() {

		final Object testingData[][] = {

			//Test #01: Access by authorized user. Expected true.
			{
				"academy2", 641, null
			},

			//Test #02: Access by anonymous user. Expected false.
			{
				null, 641, IllegalArgumentException.class
			},

			//Test #03: Access to nonexistent academy. Expected false.
			{
				"academy2", null, NullPointerException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.acceptRejectApplicationTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void listApplicationDriver() {

		final Object testingData[][] = {

			//Test #01: Correct access. Expected true.
			{
				"dancer3", 645, null
			},

			//Test #02: Attempt to access by anonymous user. Expected false.
			{
				null, 645, IllegalArgumentException.class
			},

			//Test #03: Attempt to access a nonexisting dancer. Expected false.
			{
				"dancer3", null, NullPointerException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.listApplicationTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
