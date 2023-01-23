package de.tekup.studentsabsence.entities;

import de.tekup.studentsabsence.enums.LevelEnum;
import de.tekup.studentsabsence.enums.SpecialityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Data
@ToString(exclude = "students")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Label is required")
    private String label;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @NotNull(message = "Speciality is required")
    @Enumerated(EnumType.STRING)
    private SpecialityEnum speciality;

    //ManyToMany avec subject
    @ManyToOne
    private GroupSubject groupSubject;

    //OnToMany avec Student
    @OneToMany
    private List<Student> students;


}
