package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Integer>{

	//Tutoriales de un academia
	@Query("select a.tutorials from Academy a where a.id=?1")
	Collection<Tutorial> tutorialsOfAcademy(int academyID);
	
}
