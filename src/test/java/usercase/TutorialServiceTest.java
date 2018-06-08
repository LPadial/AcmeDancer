
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
import domain.Tutorial;
import services.AcademyService;
import services.TutorialService;
import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class TutorialServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private TutorialService	tutorialService;

	@Autowired
	private AcademyService	academyService;

	//Templates


	/*
	 * 17.1: Browse the available tutorials.
	 */
	public void browseTutorialsTemplate(final String username, final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Academy a = academyService.findOne(id);
			String s = a.getActorName().toString();
			tutorialService.findAll();
			tutorialService.tutorialsOfAcademy(id);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * 18.1: An academy must be able to manage their tutorials, which includes listing, creating, editing and deleting them.
	 */
	public void manageTutorialTemplate(final String username, final Integer id, String title, String description, String video, String title2, String description2, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			//Listing
			Assert.isTrue(username == "academy1" || username == "academy2" || username == "academy3");
			tutorialService.tutorialsOfAcademy(id);

			//Creating
			Tutorial res = tutorialService.create();

			Assert.notNull(title);
			Assert.notNull(description);
			Assert.notNull(video);

			res.setTitle(title);
			res.setDescription(description);
			res.setVideo(video);

			tutorialService.save(res);

			//Editing
			Assert.notNull(title2);
			Assert.notNull(description2);

			res.setTitle(title2);
			res.setDescription(description2);

			tutorialService.save(res);

			//Deleting
			//			tutorialService.delete(res);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void browseTutorialsDriver() {

		final Object testingData[][] = {

			//Test #01: Correct access. Expected true.
			{
				null, 640, null
			},

			//Test #02: Attempt to access an erroneous entity. Expected false.
			{
				"dancer1", 650, NullPointerException.class
			},

			//Test #03: Attempt to access a nonexistent academy. Expected false.
			{
				"dancer1", null, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.browseTutorialsTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}

	@Test
	public void manageTutorialDriver() {

		final Object testingData[][] = {

			//Test #01: Correct operation. Expected true.
			{
				"academy1", 640, "title", "description", "https://www.youtube.com/watch?v=K0NGLAAlxCk", "newTitle", "newDescription", null
			},

			//Test #02: Access by unauthorized user. Expected false.
			{
				"administrator", 640, "title", "description", "https://www.youtube.com/watch?v=K0NGLAAlxCk", "newTitle", "newDescription", IllegalArgumentException.class
			},

			//Test #03: Empty fields on edition. Expected false.
			{
				"academy1", 640, "title", "description", "https://www.youtube.com/watch?v=K0NGLAAlxCk", null, null, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.manageTutorialTemplate((String) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(Class<?>) testingData[i][7]);
	}
}
