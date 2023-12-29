package com.dicii.ispw.project.models.record;

import java.time.LocalDate;

public record PersonalInfo(String name, String surname, LocalDate dateOfBirth, String fc, char gender) {}
