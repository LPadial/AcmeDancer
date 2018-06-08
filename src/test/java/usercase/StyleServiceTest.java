
package usercase;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Style;
import services.CourseService;
import services.StyleService;
import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class StyleServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private StyleService	styleService;

	@Autowired
	private CourseService	courseService;

	//Templates


	/*
	 * 5.4: Browse the catalogue of styles and navigate to the courses that offer them.
	 */
	public void browseStylesTemplate(final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Assert.notNull(styleService.findOne(id));
			
			styleService.findAll();
			courseService.coursesOfStyle(id);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * 9.1: An administrator must be able to manage the taxonomy of styles, which includes listing, creating, editing and deleting them.
	 */
	public void manageStyleTemplate(final String username, String name, String description, String name2, String description2, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			//Listing
			Assert.isTrue(username.equals("admin"));
			styleService.findAll();

			//Creating
			Style res = styleService.create();

			Assert.notNull(name);
			Assert.notNull(description);

			res.setName(name);
			res.setDescription(description);

			res = styleService.save(res);

			//Editing
			Assert.notNull(name2);
			Assert.notNull(description2);

			res.setName(name2);
			res.setDescription(description2);

			styleService.save(res);

			//Deleting
			styleService.delete(res);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void browseStylesDriver() {

		final Object testingData[][] = {

			//Test #01: Correct access. Expected true.
			{
				660, null
			},

			//Test #02: Attempt to access a nonexistent style. Expected false.
			{
				null, IllegalArgumentException.class
			},

			//Test #03: Attempt to access another entity as style. Expected false.
			{
				-1, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.browseStylesTemplate((Integer) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void manageStyleDriver() {

		final Object testingData[][] = {

			//Test #01: Correct operation. Expected true.
			{
				"admin", "style", "description", "newName", "newDescription", null
			},

			//Test #02: Access by unauthorized user. Expected false.
			{
				"academy1", "style", "description", "newName", "newDescription", IllegalArgumentException.class
			},

			//Test #03: Empty fields on creation. Expected false.
			{
				"admin", null, null, "newName", "newDescription", IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.manageStyleTemplate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);
	}
}
