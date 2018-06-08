package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Application;
import domain.Dancer;

public interface DancerRepository extends JpaRepository<Dancer, Integer>{

	//solicitudes de un bailarin
	@Query("select d.applications from Dancer d where d.id=?1")
	Collection<Application> applicationsOfDancer(int DancerID);
	
	
}
