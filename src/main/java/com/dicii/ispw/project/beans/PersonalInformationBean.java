package com.dicii.ispw.project.beans;

import java.time.LocalDate;

public class PersonalInformationBean {

    private final String name;
    private final String surname;
    private final LocalDate dateOfBirth;
    private final String fc;
    private final char gender;

    public PersonalInformationBean(String name, String surname, LocalDate dateOfBirth, String fc, char gender) {
        /*This is a constructor without syntax check and is used by controller*/
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.fc = fc;
        this.gender = gender;
    }

    //funzioni di controllo e gestione delle eccezioni


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getFc() {
        return fc;
    }

    public char getGender() {
        return gender;
    }
}
