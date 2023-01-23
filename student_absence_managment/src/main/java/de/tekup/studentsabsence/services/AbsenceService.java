package de.tekup.studentsabsence.services;

import de.tekup.studentsabsence.entities.Absence;
import de.tekup.studentsabsence.entities.Student;

import java.util.List;

public interface AbsenceService {
    List<Absence> getAllAbsences();

    List<Absence> getAllAbsencesByGroupId(Long id);

    List<Absence> getAllAbsencesByStudentId(Long sid);

    List<Absence> getAllAbsencesByStudentIdAndSubjectId(Long sid, Long id);

    List<Absence> getAllAbsencesByGroupIdAndSubjectId(Long gid, Long id);

    Absence getAbsenceById(Long id);

    Absence addAbsence(Absence absence);

    Absence addAbsenceByStudentId(Student s, Absence absence);
    Absence deleteAbsence(Long id);

    float hoursCountByStudent(Long sid);

    float hoursCountByGroupAndSubject(Long gid, Long id);

    float hoursCountByStudentAndSubject(Long sid, Long id);

    float countHours(List<Absence> absences);
}
