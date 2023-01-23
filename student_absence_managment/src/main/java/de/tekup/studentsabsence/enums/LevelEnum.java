package de.tekup.studentsabsence.enums;

public enum LevelEnum {
    LEVEL_1("LEVEL_1"),
    LEVEL_2("LEVEL_2"),
    LEVEL_3("LEVEL_3");

    private String level;

    LevelEnum(String level) {
        this.level = level;
    }

    String value() {
        return level;
    }

}
