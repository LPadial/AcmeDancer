
package repositories;

import java.lang.reflect.Array;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Administrator;
import domain.Tutorial;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	//El mínimo, media, desviación estándar y máximo de cursos por academia.
	@Query("select min(a.courses.size),avg(a.courses.size),stddev(a.courses.size),max(a.courses.size) from Academy a")
	Object[] minAvgSdMaxCoursesPerAcademy();

	//El mínimo, media, desviación estándar y máximo de solicitudes por curso.
	@Query("select min(c.applications.size),avg(c.applications.size),stddev(c.applications.size),max(c.applications.size) from Course c")
	Object[] minAvgSdMaxApplicationsPerCourse();

	//El mínimo, media y máximo de tutoriales por academia.
	@Query("select min(a.tutorials.size),avg(a.tutorials.size),max(a.tutorials.size) from Academy a")
	Object[] minAvgMaxTutorialsPerAcademy();

	//El mínimo, media y máximo de veces que un tutorial se muestra.
	@Query("select min(t.numShows),avg(t.numShows),max(t.numShows) from Tutorial t")
	Object[] minAvgMaxTutorialNumShows();

	//La lista de tutoriales, ordenados en orden descendente de acuerdo al
	//número de veces que se han visto.
	@Query("select t from Tutorial t order by t.numShows desc")
	List<Tutorial> tutorialsOrderByNumShowsDes();

	//La media de chirps por actor.
	@Query("select a.actorName, ((a.chirps.size * 1.0) / (select count(c) from Chirp c)) from Actor a")
	List<Array[]> avgChirpsPerActor();

	//La media de suscripciones por actor.
	@Query("select a.actorName, ((a.follower.size * 1.0) / (select count(c) from Actor c)) from Actor a")
	List<Array[]> avgSubscriptionPerActor();
}
