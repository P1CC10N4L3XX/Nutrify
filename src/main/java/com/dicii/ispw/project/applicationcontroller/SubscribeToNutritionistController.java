package com.dicii.ispw.project.applicationcontroller;

import com.dicii.ispw.project.beans.NutritionistBean;
import com.dicii.ispw.project.database.dao_classes.NutritionistDao;
import com.dicii.ispw.project.models.Nutritionist;

import java.util.ArrayList;
import java.util.List;

public class SubscribeToNutritionistController {
    public List<NutritionistBean> getNutritionistList(int limitNumber,int offset){
        NutritionistDao nutritionistDAO = new NutritionistDao();
        List<Nutritionist> nutritionistList = new ArrayList<>(nutritionistDAO.getNutritionistList(limitNumber,offset));
        List<NutritionistBean> nutritionistBeanList = new ArrayList<>();
        String email;
        String name;
        String surname;
        String description;
        String dateOfBirth;
        String iva;
        String iban;
        String cost;
        for(int i=0;i<nutritionistList.size();i++){
            email=nutritionistList.get(i).getEmail();
            name=nutritionistList.get(i).getName();
            surname=nutritionistList.get(i).getSurname();
            description=nutritionistList.get(i).getDescription();
            dateOfBirth=nutritionistList.get(i).getDateOfBirth();
            iva=nutritionistList.get(i).getIva();
            iban=nutritionistList.get(i).getIban();
            cost=nutritionistList.get(i).getCosto();
            nutritionistBeanList.add(new NutritionistBean(email,name,surname,description,dateOfBirth,iva,iban,cost));
        }
        return nutritionistBeanList;
    }
}
