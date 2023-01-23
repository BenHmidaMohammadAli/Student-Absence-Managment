package de.tekup.studentsabsence.services.impl;

import de.tekup.studentsabsence.entities.*;
import de.tekup.studentsabsence.repositories.AbsenceRepository;
import de.tekup.studentsabsence.services.AbsenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AbsenceServiceImp implements AbsenceService {
    private final AbsenceRepository absenceRepository;

    @Override
    public List<Absence> getAllAbsences() {
        List<Absence> absences = new ArrayList<>();
        absenceRepository.findAll().forEach(absences::add);
        return absences;
    }

    @Override
    public List<Absence> getAllAbsencesByGroupId(Long id) {
        List<Absence> absences = new ArrayList<>();
        absenceRepository.findAllByStudent_Group_Id(id).forEach(absences::add);
        return absences;
    }

    @Override
    public List<Absence> getAllAbsencesByStudentId(Long sid) {
        List<Absence> absences = new ArrayList<>();
        absenceRepository.findAllByStudent_Sid(sid).forEach(absences::add);
        return absences;
    }

    @Override
    public List<Absence> getAllAbsencesByStudentIdAndSubjectId(Long sid, Long id) {
        List<Absence> absences = new ArrayList<>();
        absenceRepository.findAllByStudent_SidAndSubject_Id(sid,id).forEach(absences::add);
        return absences;
    }

    @Override
    public List<Absence> getAllAbsencesByGroupIdAndSubjectId(Long gid, Long id) {
        List<Absence> absences = new ArrayList<>();
        absenceRepository.findAllByStudent_Group_IdAndSubject_Id(gid, id).forEach(absences::add);
        return absences;
    }

    @Override
    public Absence getAbsenceById(Long id) {
        return absenceRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Absence With ID: " + id));
    }

    @Override
    public Absence addAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }

    @Override
    public Absence addAbsenceByStudentId(Student s ,Absence absence) {
        return absenceRepository.save(
                new Absence(absence.getId(), absence.getStartDate(), absence.getHours(),absence.getSubject(), s)
        );

    }

    @Override
    public Absence deleteAbsence(Long id) {
        Absence absence = getAbsenceById(id);
        absenceRepository.delete(absence);
        return absence;
    }

    @Override
    public float hoursCountByStudent(Long sid) {
        List<Absence> absences = getAllAbsencesByStudentId(sid);
        return countHours(absences);
    }

    @Override
    public float hoursCountByGroupAndSubject(Long gid, Long id) {
        List<Absence> absences = getAllAbsencesByGroupIdAndSubjectId(gid, id);
        return countHours(absences);
    }

    @Override
    public float hoursCountByStudentAndSubject(Long sid, Long id) {
        List<Absence> absences = getAllAbsencesByStudentIdAndSubjectId(sid, id);
        return countHours(absences);
    }

    public float countHours(List<Absence> absences) {
        float NumberHours =0 ;
        for(Absence i: absences){
            NumberHours = NumberHours+i.getHours() ;
        }
        return NumberHours;
    }

}
