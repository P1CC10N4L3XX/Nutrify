package com.dicii.ispw.project.models;

public class DiabeticDecorator extends NutritionalPlanDayDecorator {

  //  in base ai valori presi decidero tramite una formula i valori glicemici
    private String valoreGlicemicoColazione;

    private String valoreGlicemicoPranzo;

    private String valoreGlicemicoCena;

    public DiabeticDecorator(NutritionalPlanDay nutritionalplanDay){
        super(nutritionalplanDay);

    }


  public String computeValue(float quantita){

    String valore=null;
    if(quantita>30 ){
      valore="ALTO";
    }if(quantita>20 && quantita<30){
      valore="MEDIO";
    }if(quantita<20){
      valore="BASSO";
    }
    System.out.println(valore);
    return valore;
  }

  @Override
  public String getQuantitaColazione() {
    String preliminaryResults = super.getQuantitaColazione();
    valoreGlicemicoColazione = this.computeValue(Float.parseFloat(preliminaryResults));

    return preliminaryResults + valoreGlicemicoColazione;
  }

  public String getQuantitaPranzo() {
    String preliminaryResults = super.getQuantitaColazione();
    valoreGlicemicoPranzo = this.computeValue(Float.parseFloat(preliminaryResults));

    return preliminaryResults + valoreGlicemicoColazione;
  }

  public String getCena() {
    String preliminaryResults = super.getQuantitaColazione();
    valoreGlicemicoCena = this.computeValue(Float.parseFloat(preliminaryResults));

    return preliminaryResults + valoreGlicemicoColazione;
  }



}