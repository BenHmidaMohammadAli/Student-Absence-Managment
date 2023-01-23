package de.tekup.studentsabsence.repositories;

import de.tekup.studentsabsence.entities.Absence;
import de.tekup.studentsabsence.entities.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, String> {

    Image findImageById( String id) ;
}
