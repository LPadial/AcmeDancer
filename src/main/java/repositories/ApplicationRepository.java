package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{

	//Solicitudes de un curso
	@Query("select c.applications from Course c where c.id=?1")
	Collection<Application> applicationsOfCourse(int courseID);
	
	//Solicitudes totales de la academia(todos los cursos)
	@Query("select c.applications from Academy a join a.courses c where a.id=?1")
	Collection<Application> allApplicationsOfAcademy(int AcademyID);
	
	//Solicitudes pendientes
	@Query("select p from Academy a join a.courses c join c.applications p join p.statusApplication app where a.id=?1 AND app.value='PENDING'")
	Collection<Application> applicationsPendingOfAcademy(int academyID);
	
	//Solicitudes aprobadas y denegadas
	@Query("select p from Academy a join a.courses c join c.applications p join p.statusApplication app where a.id=?1 AND (app.value='ACCEPTED' OR app.value='REJECTED')")
	Collection<Application> applicationsAcceptedOrRejectedOfAcademy(int academyID);
	
	//Aplicaciones pendientes de un curso
	@Query("select p from Course c join c.applications p join p.statusApplication app where c.id=?1 AND app.value='PENDING'")
	Collection<Application> applicationsPendingOfCourse(int courseId);
}
