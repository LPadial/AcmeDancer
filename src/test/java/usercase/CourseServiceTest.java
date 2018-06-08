
package usercase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Academy;
import domain.Course;
import domain.LevelCourse;
import domain.Style;
import services.AcademyService;
import services.ApplicationService;
import services.CourseService;
import services.StyleService;
import utilities.AbstractTest;

@Transactional
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
public class CourseServiceTest extends AbstractTest {

	//The SUT

	@Autowired
	private CourseService		courseService;

	@Autowired
	private AcademyService		academyService;

	@Autowired
	private StyleService		styleService;

	@Autowired
	private ApplicationService	applicationService;

	//Templates


	/*
	 * 5.3: Browse the catalogue of academies and navigate to the courses they offer.
	 */
	public void browseCoursesTemplate(final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Course c = courseService.findOne(id);
			String s = c.getTitle().toString();
			courseService.findAll();
			academyService.academyOfCourse(id);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * 5.5: Search for a course using a single keyword that must appear somewhere in its title
	 * or the name or the description of the corresponding style.
	 */
	public void searchCourseTemplate(Object keyWord, final Class<?> expected) {
		Class<?> caught = null;

		try {

			Assert.isTrue(keyWord.getClass().equals(String.class));
			String s = keyWord.toString();
			courseService.findCourses(s);

		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * 7.1: An academy must be able to manage their courses, which includes listing, creating, editing and deleting them.
	 */
	public void manageCourseTemplate(final String username, final Integer id, String title, Style style, String level, Date start, Date end, String dayWeek, String time, String title2, Style style2, String level2, Date start2, Date end2, String dayWeek2,
		String time2, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			//Listing
			Assert.isTrue(username == "academy1" || username == "academy2" || username == "academy3");
			Academy a = academyService.findOne(id);
			courseService.coursesOfAcademy(id);

			//Creating
			Course res;
			LevelCourse l1 = new LevelCourse();
			LevelCourse l2 = new LevelCourse();
			l1.setValue(level);
			l2.setValue(level2);
			res = courseService.create();
			DateFormat df = new SimpleDateFormat("HH:mm");
			Date d1 = df.parse(time);
			Date d2 = df.parse(time2);

			Assert.notNull(title);
			Assert.notNull(style);
			Assert.notNull(level);
			Assert.notNull(start);
			Assert.notNull(end);
			Assert.notNull(dayWeek);
			Assert.notNull(time);

			res.setTitle(title);
			res.setStyle(style);
			res.setLevelCourse(l1);
			res.setStart(start);
			res.setEnd(end);
			res.setDayWeek(dayWeek);
			res.setTime(d1);
			res.setAcademy(a);

			courseService.save(res);

			//Editing
			Assert.notNull(title2);
			Assert.notNull(style2);
			Assert.notNull(level2);
			Assert.notNull(start2);
			Assert.notNull(end2);
			Assert.notNull(dayWeek2);
			Assert.notNull(time2);

			res.setTitle(title2);
			res.setStyle(style2);
			res.setLevelCourse(l2);
			res.setStart(start2);
			res.setEnd(end2);
			res.setDayWeek(dayWeek2);
			res.setTime(d2);

			courseService.save(res);

			//Deleting
			courseService.delete(res);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	/*
	 * 8.1: A dancer must be able to apply for a course.
	 */
	public void applyCourseTemplate(final String username, final Integer id, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			Assert.isTrue(username == "dancer1" || username == "dancer2" || username == "dancer3");
			Course c = courseService.findOne(id);
			applicationService.apply(c);

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Drivers

	@Test
	public void browseCoursesDriver() {

		final Object testingData[][] = {

			//Test #01: Correct access. Expected true.
			{
				670, null
			},

			//Test #02: Attempt to access a nonexistent course. Expected false.
			{
				null, IllegalArgumentException.class
			},

			//Test #03: Attempt to access another entity as course. Expected false.
			{
				650, NullPointerException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.browseCoursesTemplate((Integer) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void searchCourseDriver() {

		final Object testingData[][] = {

			//Test #01: Search using a proper keyword. Expected true.
			{
				"curso", null
			},

			//Test #02: Search using a number. Expected false.
			{
				12345, IllegalArgumentException.class
			},

			//Test #03: Introduce a null object. Expected false.
			{
				null, NullPointerException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.searchCourseTemplate(testingData[i][0], (Class<?>) testingData[i][1]);
	}

	@Test
	public void manageCourseDriver() {

		final Object testingData[][] = {

			//Test #01: All parameters correct. Expected true.
			{
				"academy1", 640, "title", styleService.findOne(660), "BEGINNER", new Date(10 / 10 / 2017), new Date(10 / 10 / 2018), "Monday", "20:10", "newTitle", styleService.findOne(661), "INTERMEDIATE", new Date(11 / 10 / 2017),
				new Date(11 / 10 / 2018), "Tuesday", "21:20", null
			},

			//Test #02: Attempt to access as anonymous user. Expected false.
			{
				null, 640, "title", styleService.findOne(660), "BEGINNER", new Date(10 / 10 / 2017), new Date(20 / 10 / 2017), "Monday", "20:10", "newTitle", styleService.findOne(661), "INTERMEDIATE", new Date(11 / 10 / 2017), new Date(21 / 10 / 2017),
				"Tuesday", "21:20", IllegalArgumentException.class
			},

			//Test #03: Attempt to access as unauthorized user. Expected false.
			{
				"dancer1", 640, "title", styleService.findOne(660), "BEGINNER", new Date(10 / 10 / 2017), new Date(20 / 10 / 2017), "Monday", "20:10", "newTitle", styleService.findOne(661), "INTERMEDIATE", new Date(11 / 10 / 2017),
				new Date(21 / 10 / 2017), "Tuesday", "21:20", IllegalArgumentException.class
			},

			//Test #04: Empty fields on creation. Expected false.
			{
				"academy1", 640, null, styleService.findOne(660), "BEGINNER", null, new Date(20 / 10 / 2017), null, "20:10", "newTitle", styleService.findOne(661), "INTERMEDIATE", new Date(11 / 10 / 2017), new Date(21 / 10 / 2017), "Tuesday", "21:20",
				IllegalArgumentException.class
			},

			//Test #05: Empty fields on edition. Expected false.
			{
				"academy1", 640, "title", styleService.findOne(660), "BEGINNER", new Date(10 / 10 / 2017), new Date(20 / 10 / 2017), "Monday", "20:10", null, styleService.findOne(661), "INTERMEDIATE", new Date(11 / 10 / 2017), null, "Tuesday", null,
				NullPointerException.class
			},

			//Test #06: Attempt to introduce an erroneous style on creation. Expected false.
			{
				"academy1", 640, "title", styleService.findOne(0), "BEGINNER", new Date(10 / 10 / 2017), new Date(20 / 9 / 2017), "Monday", "20:10", "newTitle", styleService.findOne(661), "INTERMEDIATE", new Date(11 / 10 / 2017), new Date(21 / 10 / 2017),
				"Tuesday", "21:20", IllegalArgumentException.class
			},

			//Test #07: Attempt to introduce an erroneous style on edition. Expected false.
			{
				"academy1", 640, "title", styleService.findOne(660), "BEGINNER", new Date(10 / 10 / 2017), new Date(20 / 10 / 2017), "Monday", "20:10", "newTitle", styleService.findOne(0), "INTERMEDIATE", new Date(111 / 10 / 2017), new Date(21 / 9 / 2017),
				"Tuesday", "21:20", IllegalArgumentException.class
			},

			//Test #08: Attempt to introduce erroneous level on creation. Expected false.
			{
				"academy1", 640, "title", styleService.findOne(660), "level", new Date(10 / 10 / 2017), new Date(20 / 10 / 2017), "Monday", "20:10", "newTitle", styleService.findOne(661), "INTERMEDIATE", new Date(11 / 10 / 2017), new Date(21 / 10 / 2017),
				"Tuesday", "21:20", ConstraintViolationException.class
			},

			//Test #09: Attempt to introduce an erroneous hour format on creation. Expected false.
			{
				"academy1", 640, "title", styleService.findOne(9999), "BEGINNER", new Date(10 / 10 / 2017), new Date(20 / 10 / 2017), "Monday", "6:10PM", "newTitle", styleService.findOne(661), "INTERMEDIATE", new Date(11 / 10 / 2017),
				new Date(21 / 10 / 2017), "Tuesday", "21:20", ConstraintViolationException.class
			},

			//Test #10: Attempt to introduce HTML code on edition. Expected false.
			{
				"academy1", 640, "title", styleService.findOne(660), "BEGINNER", new Date(10 / 10 / 2017), new Date(20 / 10 / 2017), "Monday", "20:10", "<!DOCTYPE html><html><body><h1>This is heading 1</h1></body></html>", styleService.findOne(661),
				"INTERMEDIATE", new Date(11 / 10 / 2017), new Date(21 / 10 / 2017), "Tuesday", "21:20", ConstraintViolationException.class
			},

		};
		for (int i = 0; i < testingData.length; i++)
			this.manageCourseTemplate((String) testingData[i][0], (Integer) testingData[i][1], (String) testingData[i][2], (Style) testingData[i][3], (String) testingData[i][4], (Date) testingData[i][5], (Date) testingData[i][6],
				(String) testingData[i][7], (String) testingData[i][8], (String) testingData[i][9], (Style) testingData[i][10], (String) testingData[i][11], (Date) testingData[i][12], (Date) testingData[i][13], (String) testingData[i][14],
				(String) testingData[i][15], (Class<?>) testingData[i][16]);
	}

	@Test
	public void applyCourseDriver() {

		final Object testingData[][] = {

			//Test #01: Correct application. Expected true.
			{
				"dancer1", 672, null
			},

			//Test #02: Attempt by unauthorized user. Expected false.
			{
				"academy1", 672, IllegalArgumentException.class
			},

			//Test #03: Attempt to access nonexistent course. Expected false.
			{
				"dancer1", null, IllegalArgumentException.class
			}

		};
		for (int i = 0; i < testingData.length; i++)
			this.applyCourseTemplate((String) testingData[i][0], (Integer) testingData[i][1], (Class<?>) testingData[i][2]);
	}
}
