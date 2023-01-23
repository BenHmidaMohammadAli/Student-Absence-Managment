package de.tekup.studentsabsence.enums;

public enum SpecialityEnum {
    TIC("TIC"),
    GLSI("GLSI"),
    DSEN("DSEN"),
    DMWM("DMWM"),
    SSIR("SSIR");
    private String speciality;

    SpecialityEnum(String speciality) {
        this.speciality = speciality;
    }

    String value() {
        return speciality;
    }
}
