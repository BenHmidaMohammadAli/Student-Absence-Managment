package de.tekup.studentsabsence.services;

import de.tekup.studentsabsence.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image getImage(String id);

    Image addImage(MultipartFile image) throws IOException;
}
