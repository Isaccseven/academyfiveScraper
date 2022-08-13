package de.lucahenn.academyfive.constant;

import lombok.Getter;

@Getter
public enum GradeConstants {
    LOGIN_URL("https://asw-dualesstudium.academyfive.net/community/login"),
    GRADE_URL("https://asw-dualesstudium.academyfive.net/noteneinsicht.php");

    private final String constant;

    private GradeConstants(String constant) {
        this.constant = constant;
    }

}
