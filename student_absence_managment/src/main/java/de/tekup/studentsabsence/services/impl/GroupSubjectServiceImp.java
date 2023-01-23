package de.tekup.studentsabsence.services.impl;

import de.tekup.studentsabsence.entities.Group;
import de.tekup.studentsabsence.entities.GroupSubject;
import de.tekup.studentsabsence.entities.GroupSubjectKey;
import de.tekup.studentsabsence.entities.Subject;
import de.tekup.studentsabsence.repositories.GroupSubjectRepository;
import de.tekup.studentsabsence.services.GroupService;
import de.tekup.studentsabsence.services.GroupSubjectService;
import de.tekup.studentsabsence.services.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupSubjectServiceImp implements GroupSubjectService {
    private final GroupSubjectRepository groupSubjectRepository;

    private final GroupService groupService;

    private final SubjectService subjectService;

    @Override
    public void addSubjectToGroup(Group group, Subject subject, float hours) {
        groupSubjectRepository.save(
                new GroupSubject(
                new GroupSubjectKey(group.getId(),subject.getId()
                ),
                group,
                subject,
                hours
        ));
    }

    @Override
    public List<GroupSubject> getSubjectsByGroupId(Long id) {
        Group group = groupService.getGroupById(id);
        return new ArrayList<>(groupSubjectRepository.findAllByGroup(group));
    }

    @Override
    public void deleteSubjectFromGroup(Long gid, Long sid) {
        Group group = groupService.getGroupById(gid);
        Subject subject =subjectService.getSubjectById(sid);

        List<GroupSubject> groupSubject = groupSubjectRepository.findGroupSubjecByGroupandSubject(group,subject);
        for ( GroupSubject i:groupSubject){
            groupSubjectRepository.delete(i);

        }
    }

}
