
package usercase;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import services.AdministratorService;
import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class AdministratorServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private AdministratorService administratorService;

	//Templates


	/*
	 * 9.2: An administrator must be able to display a dashboard with site information.
	 */
	public void dashboardTemplate(final String username, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Assert.isTrue(username == "admin");
			administratorService.minAvgSdMaxCoursesPerAcademy();
			administratorService.minAvgSdMaxApplicationsPerCourse();
			administratorService.minAvgMaxTutorialsPerAcademy();
			administratorService.minAvgMaxTutorialNumShows();
			administratorService.avgChirpsPerActor();
			administratorService.tutorialsOrderByNumShowsDes();
			administratorService.avgSubscriptionPerActor();

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void dashboardDriver() {

		final Object testingData[][] = {

			//Test #01: Access by administrator. Expected true.
			{
				"admin", null
			},

			//Test #02: Attempt to access by anonymous user. Expected false.
			{
				null, IllegalArgumentException.class
			},

			//Test #03: Attempt to access by unauthorized user. Expected false.
			{
				"academy2", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.dashboardTemplate((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}
}
