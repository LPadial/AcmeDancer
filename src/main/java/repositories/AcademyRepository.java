package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Academy;

public interface AcademyRepository extends JpaRepository<Academy, Integer> {

	// Academia que ofrece el curso.
	@Query("select c.academy from Course c where c.id=?1")
	Academy academyOfCourse(int courseID);
}
