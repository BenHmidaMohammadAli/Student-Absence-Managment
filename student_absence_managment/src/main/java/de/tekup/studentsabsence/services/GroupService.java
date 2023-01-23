package de.tekup.studentsabsence.services;

import de.tekup.studentsabsence.entities.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();

    Group getGroupById(Long id);

    Group addGroup(Group group);

    Group updateGroup(Group group);

    Group deleteGroup(Long id);



}
