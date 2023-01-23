package de.tekup.studentsabsence.repositories;

import de.tekup.studentsabsence.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

    public Student getStudentsByGroupId(long gid );

}
