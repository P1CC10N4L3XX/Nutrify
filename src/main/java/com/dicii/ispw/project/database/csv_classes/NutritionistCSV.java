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

        myRecord[NutritionistAttributesOrder.getIndexNutritionistEmail()] = nutritionist.getEmail();
        myRecord[NutritionistAttributesOrder.getIndexNutritionistPassword()] = nutritionist.getPassword();

        csvWriter.writeNext(myRecord);
        try {
            csvWriter.flush();
            csvWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Nutritionist selectInfoNutritionist(String nutritionistEmail){
        CSVReader csvReader;
        try{
            csvReader = new CSVReader(new BufferedReader(new FileReader(fd)));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        String[] myRecord;
        try {
            while ((myRecord = csvReader.readNext())!=null){
                if(myRecord[NutritionistAttributesOrder.getIndexNutritionistEmail()].equals(nutritionistEmail)){
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
        String[] myRecord;
        try{
            while((myRecord = csvReader.readNext())!=null){
                int posEmail = NutritionistAttributesOrder.getIndexNutritionistEmail();
                if(myRecord[posEmail].equals(nutritionist.getEmail())){
                    myRecord[NutritionistAttributesOrder.getIndexNutritionistName()] = nutritionist.getEmail();
                    myRecord[NutritionistAttributesOrder.getIndexNutritionistSurname()] = nutritionist.getSurname();
                    myRecord[NutritionistAttributesOrder.getIndexNutritionistBirth()] = nutritionist.getDateOfBirth();
                    myRecord[NutritionistAttributesOrder.getIndexNutritionistIban()] = nutritionist.getIban();
                    myRecord[NutritionistAttributesOrder.getIndexNutritionistIva()] = nutritionist.getIva();
                    myRecord[NutritionistAttributesOrder.getIndexNutritionistCost()] = nutritionist.getCosto();
                    myRecord[NutritionistAttributesOrder.getIndexNutritionistDescription()] = nutritionist.getDescription();
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
                int posEmail = NutritionistAttributesOrder.getIndexNutritionistEmail();
                int posPass = NutritionistAttributesOrder.getIndexNutritionistPassword();
                if(myRecord[posEmail].equals(nutritionist.getEmail()) && myRecord[posPass].equals(nutritionist.getPassword())){
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
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        String[] myRecord;
        int i = 0;
        try {
            while ((myRecord = csvReader.readNext())!=null){
                if(i>=from && i<=to){
                    String email=myRecord[NutritionistAttributesOrder.getIndexNutritionistEmail()];
                    String name=myRecord[NutritionistAttributesOrder.getIndexNutritionistName()];
                    String surname = myRecord[NutritionistAttributesOrder.getIndexNutritionistSurname()];
                    String dateOfBirth = myRecord[NutritionistAttributesOrder.getIndexNutritionistBirth()];
                    String description = myRecord[NutritionistAttributesOrder.getIndexNutritionistDescription()];
                    String iva = myRecord[NutritionistAttributesOrder.getIndexNutritionistIva()];
                    String iban = myRecord[NutritionistAttributesOrder.getIndexNutritionistIban()];
                    String costo = myRecord[NutritionistAttributesOrder.getIndexNutritionistCost()];
                    nutritionistList.add(new Nutritionist(email,name,surname,dateOfBirth,description,iva,iban,costo));
                }
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
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
