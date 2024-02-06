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
    private final File fd;
    private final int ATTRIBUTES_NUMBER = 9;

    public NutritionistCSV(){
        this.fd = new File(CSV_FILE_NAME);

        if(!fd.exists()){
            try{
                fd.createNewFile();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public void saveNutritionist(UserCredentials nutritionist) throws DuplicatedUserException {
        boolean duplicatedRecordEmail;

        duplicatedRecordEmail = (selectInfoNutritionist(nutritionist.getEmail()) != null);

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

        record[NutritionistAttributesOrder.getIndexNutritionistEmail()] = nutritionist.getEmail();
        record[NutritionistAttributesOrder.getIndexNutritionistPassword()] = nutritionist.getPassword();

        csvWriter.writeNext(record);
        try {
            csvWriter.flush();
            csvWriter.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private Nutritionist selectInfoNutritionist(String nutritionistEmail){
        CSVReader csvReader;
        try{
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        String[] record;
        try {
            while ((record = csvReader.readNext())!=null){
                if(record[NutritionistAttributesOrder.getIndexNutritionistEmail()].equals(nutritionistEmail)){
                    return new Nutritionist(nutritionistEmail);
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public void saveNutritionistAll(Nutritionist nutritionist) {
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
                int posEmail = NutritionistAttributesOrder.getIndexNutritionistEmail();
                if(record[posEmail].equals(nutritionist.getEmail())){
                    record[NutritionistAttributesOrder.getIndexNutritionistName()] = nutritionist.getEmail();
                    record[NutritionistAttributesOrder.getIndexNutritionistSurname()] = nutritionist.getSurname();
                    record[NutritionistAttributesOrder.getIndexNutritionistBirth()] = nutritionist.getDateOfBirth();
                    record[NutritionistAttributesOrder.getIndexNutritionistIban()] = nutritionist.getIban();
                    record[NutritionistAttributesOrder.getIndexNutritionistIva()] = nutritionist.getIva();
                    record[NutritionistAttributesOrder.getIndexNutritionistCost()] = nutritionist.getCosto();
                    record[NutritionistAttributesOrder.getIndexNutritionistDescription()] = nutritionist.getDescription();
                }
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
    public UserCredentials loadNutritionistByCredentials(UserCredentials nutritionist) throws NotExistentUserException {
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
                int posEmail = NutritionistAttributesOrder.getIndexNutritionistEmail();
                int posPass = NutritionistAttributesOrder.getIndexNutritionistPassword();
                if(record[posEmail].equals(nutritionist.getEmail()) && record[posPass].equals(nutritionist.getPassword())){
                    recordFound = true;
                    break;
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        if(recordFound){
            return nutritionist;
        }else{
            throw new NotExistentUserException();
        }
    }

    @Override
    public List<Nutritionist> getNutritionistList(int from, int to) {
        CSVReader csvReader;
        List<Nutritionist> nutritionistList = new ArrayList<Nutritionist>();
        try {
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        String[] record;
        int i = 0;
        try {
            while ((record = csvReader.readNext())!=null){
                if(i>=from && i<=to){
                    String email=record[NutritionistAttributesOrder.getIndexNutritionistEmail()];
                    String name=record[NutritionistAttributesOrder.getIndexNutritionistName()];
                    String surname = record[NutritionistAttributesOrder.getIndexNutritionistSurname()];
                    String dateOfBirth = record[NutritionistAttributesOrder.getIndexNutritionistBirth()];
                    String description = record[NutritionistAttributesOrder.getIndexNutritionistDescription()];
                    String iva = record[NutritionistAttributesOrder.getIndexNutritionistIva()];
                    String iban = record[NutritionistAttributesOrder.getIndexNutritionistIban()];
                    String costo = record[NutritionistAttributesOrder.getIndexNutritionistCost()];
                    nutritionistList.add(new Nutritionist(email,name,surname,dateOfBirth,description,iva,iban,costo));
                }
                i++;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return nutritionistList;
    }
    private static class NutritionistAttributesOrder{
        public static int getIndexNutritionistEmail(){
            return 0;
        }
        public static int getIndexNutritionistPassword(){
            return 1;
        }
        public static int getIndexNutritionistName(){
            return 2;
        }
        public static int getIndexNutritionistSurname(){
            return 3;
        }
        public static int getIndexNutritionistBirth(){
            return 4;
        }
        public static int getIndexNutritionistIban(){
            return 5;
        }
        public static int getIndexNutritionistIva(){
            return 6;
        }
        public static int getIndexNutritionistCost(){
            return 7;
        }
        public static int getIndexNutritionistDescription(){
            return 8;
        }
    }
}
