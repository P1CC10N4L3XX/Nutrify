package com.dicii.ispw.project.database.csv_classes;

import com.dicii.ispw.project.database.dao_interfaces.NutritionistDaoInterface;
import com.dicii.ispw.project.exceptions.DuplicatedUserException;
import com.dicii.ispw.project.exceptions.NotExistentUserException;
import com.dicii.ispw.project.models.Nutritionist;
import com.dicii.ispw.project.models.UserCredentials;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class NutritionistCSV implements NutritionistDaoInterface {
    private static final String CSV_FILE_NAME = "file/NutritionistFile.csv";
    private static final int EMAIL = 0;
    private static final int PASSWORD = 1;
    private static final int NAME = 2;
    private static final int SURNAME = 3;
    private static final int BIRTH = 4;
    private static final int IBAN = 5;
    private static final int IVA = 6;
    private static final int COST = 7;
    private static final int DESCRIPTION = 8;
    private final File fd;

    public NutritionistCSV(){
        this.fd = new File(CSV_FILE_NAME);
    }
    @Override
    public void saveNutritionist(UserCredentials nutritionist) throws DuplicatedUserException {
        boolean duplicatedRecordEmail;

        duplicatedRecordEmail = (selectInfoNutritionist(nutritionist.getEmail()) != null);

        if(duplicatedRecordEmail){
            throw new DuplicatedUserException("User already exists");
        }
        saveNutritionist(this.fd,nutritionist);
    }
    public void saveNutritionist(File fd, UserCredentials nutritionist){
        CSVWriter csvWriter = null;
        try {
            csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd)));
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }

        String[] myRecord = new String[9];

        myRecord[EMAIL] = nutritionist.getEmail();
        myRecord[PASSWORD] = nutritionist.getPassword();

        csvWriter.writeNext(myRecord);
        try {
            csvWriter.flush();
            csvWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Nutritionist selectInfoNutritionist(String nutritionistEmail){
        CSVReader csvReader = null;
        try{
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        String[] myRecord;
        try {
            while ((myRecord = csvReader.readNext())!=null){
                if(myRecord[EMAIL].equals(nutritionistEmail)){
                    return new Nutritionist(nutritionistEmail);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }finally {
            try {
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public void saveNutritionistAll(Nutritionist nutritionist) {
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
                if(myRecord[EMAIL].equals(nutritionist.getEmail())){
                    myRecord[NAME] = nutritionist.getEmail();
                    myRecord[SURNAME] = nutritionist.getSurname();
                    myRecord[BIRTH] = nutritionist.getDateOfBirth();
                    myRecord[IBAN] = nutritionist.getIban();
                    myRecord[IVA] = nutritionist.getIva();
                    myRecord[COST] = nutritionist.getCosto();
                    myRecord[DESCRIPTION] = nutritionist.getDescription();
                }
            }
            csvWriter.flush();
            csvWriter.close();
            csvReader.close();
            Files.move(tmpFD.toPath(),fd.toPath(),REPLACE_EXISTING);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public UserCredentials loadNutritionistByCredentials(UserCredentials nutritionist) throws NotExistentUserException {
        CSVReader csvReader = null;
        try{
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        String[] myRecord;
        boolean recordFound = false;
        try{
            while((myRecord = csvReader.readNext())!=null){
                if(myRecord[EMAIL].equals(nutritionist.getEmail()) && myRecord[PASSWORD].equals(nutritionist.getPassword())){
                    recordFound = true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(recordFound){
            return nutritionist;
        }else{
            throw new NotExistentUserException();
        }
    }

    @Override
    public List<Nutritionist> getNutritionistList(int from, int to) {
        CSVReader csvReader = null;
        List<Nutritionist> nutritionistList = new ArrayList<Nutritionist>();
        try {
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        String[] myRecord;
        int i = 0;
        try {
            while ((myRecord = csvReader.readNext()) != null) {
                if (i >= from && i <= to) {
                    String email = myRecord[EMAIL];
                    String name = myRecord[NAME];
                    String surname = myRecord[SURNAME];
                    String dateOfBirth = myRecord[BIRTH];
                    String description = myRecord[DESCRIPTION];
                    String iva = myRecord[IVA];
                    String iban = myRecord[IBAN];
                    String costo = myRecord[COST];
                    nutritionistList.add(new Nutritionist(email, name, surname, dateOfBirth, description, iva, iban, costo));
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return nutritionistList;
    }
}
