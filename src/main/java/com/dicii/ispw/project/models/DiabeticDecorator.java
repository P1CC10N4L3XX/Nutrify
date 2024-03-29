package com.dicii.ispw.project.models;

import com.dicii.ispw.project.patterns.decorator.NutritionalPlan;

public class DiabeticDecorator extends NutritionalPlanDayDecorator {

  //  in base ai valori presi decidero tramite una formula i valori glicemici
    private String valoreGlicemicoColazione;

    private String valoreGlicemicoPranzo;

    private String valoreGlicemicoCena;

    private static final String INDICE="g indice glicemico:";

    public DiabeticDecorator(NutritionalPlan nutritionalplan){
        super(nutritionalplan);

    }


  public String computeValue(float quantita){

    String valore=null;
    if(quantita>30 ){
      valore="ALTO";
    }
    if(quantita>20 && quantita<30){
      valore="MEDIO";
    }
    if(quantita<20){
      valore="BASSO";
    }

    return valore;
  }

  @Override
  public String getQuantitaColazione() {
    String preliminaryResults = super.getQuantitaColazione();
    valoreGlicemicoColazione = this.computeValue(Float.parseFloat(preliminaryResults));

    return preliminaryResults + INDICE +valoreGlicemicoColazione;
  }

  @Override
  public String getQuantitaPranzo() {
    String preliminaryResults = super.getQuantitaPranzo();
    valoreGlicemicoPranzo = this.computeValue(Float.parseFloat(preliminaryResults));

    return preliminaryResults + INDICE + valoreGlicemicoPranzo;
  }

  @Override
  public String getQuantitaCena() {
    String preliminaryResults = super.getQuantitaCena();
    valoreGlicemicoCena = this.computeValue(Float.parseFloat(preliminaryResults));

    return preliminaryResults  + INDICE + valoreGlicemicoCena;
  }



}
