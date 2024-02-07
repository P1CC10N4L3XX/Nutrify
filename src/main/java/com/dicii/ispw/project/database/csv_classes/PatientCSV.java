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

    private static final int EMAIL = 0;
    private static final int PASSWORD = 1;
    private static final int NAME = 2;
    private static final int SURNAME = 3;
    private static final int BIRTH = 4;
    private static final int WEIGHT = 5;
    private static final int HEIGHT = 6;
    private static final int NUTRITIONIST = 7;
    private static final int ILLNESSES = 8;

    private final File fd;




    public PatientCSV() {
        this.fd = new File(CSV_FILE_NAME);
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
        String[] myRecord = new String[9];
        try (CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd)));){

            myRecord[EMAIL] = patient.getEmail();
            myRecord[PASSWORD] = patient.getPassword();
            csvWriter.writeNext(myRecord);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public void savePatientAll(Patient patient) {
        CSVReader csvReader = null;
        CSVWriter csvWriter = null;
        File tmpFD = null;
        try{
            tmpFD = File.createTempFile("dao","tmp");
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
            csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(tmpFD)));
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        String[] myRecord;
        try{
            while((myRecord = csvReader.readNext())!=null){
                if(myRecord[EMAIL].equals(patient.getEmail())){
                    myRecord[NAME] = patient.getName();
                    myRecord[SURNAME]=patient.getSurname();
                    myRecord[BIRTH] = patient.getDateOfBirth();
                    myRecord[WEIGHT] = patient.getWeight();
                    myRecord[HEIGHT] = patient.getHeight();
                }
                csvWriter.writeNext(myRecord);
            }
            csvWriter.flush();
            csvWriter.close();
            csvReader.close();
            Files.move(tmpFD.toPath(),fd.toPath(),REPLACE_EXISTING);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public UserCredentials loadPatientByCredentials(UserCredentials userCredentials) throws NotExistentUserException {
        String[] myRecord;
        boolean recordFound = false;
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));){
            while((myRecord = csvReader.readNext())!=null){
                if(myRecord[EMAIL].equals(userCredentials.getEmail()) && myRecord[PASSWORD].equals(userCredentials.getPassword())){
                    recordFound = true;
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        if(recordFound){
            return userCredentials;
        }else{
            throw new NotExistentUserException("This user doesn't exist");
        }
    }

    @Override
    public void setSubscriptionRequestPatient(Patient patient, Nutritionist nutritionist) {
        String[] myRecord;
        File tmpFD = null;
        try {
            tmpFD = File.createTempFile("dao","tmp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd))); CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(tmpFD)))){
            while ((myRecord = csvReader.readNext())!=null){
                if(myRecord[EMAIL].equals(patient.getEmail())){
                    myRecord[NUTRITIONIST] = nutritionist.getEmail();
                }
                csvWriter.writeNext(myRecord);
            }
            csvWriter.flush();
            Files.move(tmpFD.toPath(),fd.toPath(),REPLACE_EXISTING);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public User loadNutritionistSubscribed(String patientEmail) throws NotExistentUserException {
        String[] myRecord;
        try (CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));){
            while((myRecord = csvReader.readNext())!=null){
                if(myRecord[EMAIL].equals(patientEmail)){
                    return new User(myRecord[NUTRITIONIST]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        throw new NotExistentUserException("This user doesn't exist");
    }

    @Override
    public Patient selectInfoPatient(String emailPatient){
        String[] myRecord;
        Patient patientResult = null;
        try (CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))){
            while ((myRecord = csvReader.readNext()) != null){

                if(myRecord[EMAIL].equals(emailPatient)){
                    String email = myRecord[EMAIL];
                    String name = myRecord[NAME];
                    String surname = myRecord[SURNAME];
                    String dateOfBirth = myRecord[BIRTH];
                    String weight = myRecord[WEIGHT];
                    String height = myRecord[HEIGHT];
                    patientResult = new Patient(email,name,surname,null,dateOfBirth,weight,height);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return patientResult;
    }
}
