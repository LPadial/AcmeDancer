package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Style;

public interface StyleRepository extends JpaRepository<Style, Integer>{
	
	// Estilo del curso.
		@Query("select c.style from Course c where c.id=?1")
		Style styleOfCourse(int CourseID);

}
