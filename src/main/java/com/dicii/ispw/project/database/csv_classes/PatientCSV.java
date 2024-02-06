package com.dicii.ispw.project.database.csv_classes;

import com.dicii.ispw.project.database.dao_interfaces.PatientDaoInterface;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.Patient;
import com.dicii.ispw.project.models.User;
import com.dicii.ispw.project.models.UserCredentials;

import java.io.*;
import java.nio.file.Files;


import static java.nio.file.StandardCopyOption.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class PatientCSV implements PatientDaoInterface {

    private static final String CSV_FILE_NAME = "file/PatientFile.csv";

    private final File fd;

    private final int ATTRIBUTES_NUMBER=9;


    public PatientCSV() {
        this.fd = new File(CSV_FILE_NAME);

        if(!fd.exists()){
            try {
                fd.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void savePatient(UserCredentials patient) throws DuplicatedUserException {
        boolean duplicatedRecordEmail;
        try{
            duplicatedRecordEmail = (selectInfoPatient(patient.getEmail()) != null);
        }catch(Exception e){
            duplicatedRecordEmail = false;
        }
        if(duplicatedRecordEmail){
            throw new DuplicatedUserException("User already exists");
        }
        CSVWriter csvWriter;
        try {
            csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        String[] record = new String[ATTRIBUTES_NUMBER];

        record[PatientAttributesOrder.getIndexPatientEmail()] = patient.getEmail();
        record[PatientAttributesOrder.getIndexPatientPassword()] = patient.getPassword();

        csvWriter.writeNext(record);
        try {
            csvWriter.flush();
            csvWriter.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePatientAll(Patient patient) {
        CSVReader csvReader;
        CSVWriter csvWriter;
        File tmpFD;
        try{
            tmpFD = File.createTempFile("dao","tmp");
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(tmpFD)));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        String[] record;
        try{
            while((record = csvReader.readNext())!=null){
                int posEmail = PatientAttributesOrder.getIndexPatientEmail();
                if(record[posEmail].equals(patient.getEmail())){
                    record[PatientAttributesOrder.getIndexPatientName()] = patient.getName();
                    record[PatientAttributesOrder.getIndexPatientSurname()]=patient.getSurname();
                    record[PatientAttributesOrder.getIndexPatientBirth()] = patient.getDateOfBirth();
                    record[PatientAttributesOrder.getIndexPatientWeight()] = patient.getWeight();
                    record[PatientAttributesOrder.getIndexPatientHeight()] = patient.getHeight();
                }
                csvWriter.writeNext(record);
            }
            csvWriter.flush();
            csvWriter.close();
            csvReader.close();
            Files.move(tmpFD.toPath(),fd.toPath(),REPLACE_EXISTING);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserCredentials loadPatientByCredentials(UserCredentials userCredentials) throws NotExistentUserException {
        CSVReader csvReader;
        try{
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        String[] record;
        boolean recordFound = false;
        try{
            while((record = csvReader.readNext())!=null){
                int posEmail = PatientAttributesOrder.getIndexPatientEmail();
                int posPass = PatientAttributesOrder.getIndexPatientPassword();
                if(record[posEmail].equals(userCredentials.getEmail()) && record[posPass].equals(userCredentials.getPassword())){
                    recordFound = true;
                    break;
                }
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        if(recordFound){
            return userCredentials;
        }else{
            throw new NotExistentUserException("This user doesn't exist");
        }
    }

    @Override
    public void setSubscriptionRequestPatient(Patient patient, Nutritionist nutritionist) {
        CSVReader csvReader;
        CSVWriter csvWriter;
        File tmpFD;
        try{
            tmpFD = File.createTempFile("dao","tmp");
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(tmpFD)));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        String[] record;
        try{
            while ((record = csvReader.readNext())!=null){
                int posEmail = PatientAttributesOrder.getIndexPatientEmail();
                if(record[posEmail].equals(patient.getEmail())){
                    record[PatientAttributesOrder.getIndexPatientNutritionist()] = nutritionist.getEmail();
                }
                csvWriter.writeNext(record);
            }
            csvWriter.flush();
            csvWriter.close();
            csvReader.close();
            Files.move(tmpFD.toPath(),fd.toPath(),REPLACE_EXISTING);
        }catch(Exception e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public User loadNutritionistSubscribed(String patientEmail) throws NotExistentUserException {
        CSVReader csvReader;
        try {
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        }catch (Exception e){
            throw new RuntimeException();
        }
        String[] record;
        try{
            while((record = csvReader.readNext())!=null){
                if(record[PatientAttributesOrder.getIndexPatientEmail()].equals(patientEmail)){
                    return new User(record[PatientAttributesOrder.getIndexPatientNutritionist()]);
                }
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        throw new NotExistentUserException("This user doesn't exist");
    }

    @Override
    public Patient selectInfoPatient(String emailPatient){
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String[] record;
        Patient patientResult = null;
        try {
            while ((record = csvReader.readNext()) != null){
                int posEmail = PatientAttributesOrder.getIndexPatientEmail();

                if(record[posEmail].equals(emailPatient)){
                    String email = record[PatientAttributesOrder.getIndexPatientEmail()];
                    String name = record[PatientAttributesOrder.getIndexPatientName()];
                    String surname = record[PatientAttributesOrder.getIndexPatientSurname()];
                    String dateOfBirth = record[PatientAttributesOrder.getIndexPatientBirth()];
                    String weight = record[PatientAttributesOrder.getIndexPatientHeight()];
                    String height = record[PatientAttributesOrder.getIndexPatientHeight()];
                    patientResult = new Patient(email,name,surname,null,dateOfBirth,weight,height);
                }
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return patientResult;
    }

    private static class PatientAttributesOrder{
        public static int getIndexPatientEmail(){
            return 0;
        }
        public static int getIndexPatientPassword(){
            return 1;
        }
        public static int getIndexPatientName(){
            return 2;
        }
        public static int getIndexPatientSurname(){
            return 3;
        }
        public static int getIndexPatientBirth(){
            return 4;
        }
        public static int getIndexPatientWeight(){
            return 5;
        }
        public static int getIndexPatientHeight(){
            return 6;
        }
        public static int getIndexPatientNutritionist(){
            return 7;
        }
        public static int getIndexPatientIllness(){
            return 8;
        }
    }
}
