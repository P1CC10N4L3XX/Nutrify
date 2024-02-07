package com.dicii.ispw.project.beans;

public class PatientBean extends UserBean{
    private String weight;
    private String height;

    private IlnessesBean ilnesses;

    public PatientBean(String email,String name,String surname, String dateOfBirth, String weight, String height,IlnessesBean ilnesses){
        super(email,name,surname,dateOfBirth);
        this.weight=weight;
        this.height=height;
        this.ilnesses=ilnesses;
    }



    public PatientBean(){super();}


    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public IlnessesBean getIlnessesBean() {
        return ilnesses;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }
    public void setIlnesses(IlnessesBean ilnesses) {
        this.ilnesses=ilnesses;
    }

}
