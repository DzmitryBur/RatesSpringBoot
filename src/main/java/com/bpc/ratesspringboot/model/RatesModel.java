package com.bpc.ratesspringboot.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@JsonAutoDetect
public class RatesModel {

    public String Cur_ID;
    public Date Date;
    public String Cur_Abbreviation;
    public String Cur_Scale;
    public String Cur_Name;
    public double Cur_OfficialRate;

    public RatesModel() {

    };

    public String getCur_ID() {
        return Cur_ID;
    }

    public void setCur_ID(String cur_ID) {
        Cur_ID = cur_ID;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getCur_Abbreviation() {
        return Cur_Abbreviation;
    }

    public void setCur_Abbreviation(String cur_Abbreviation) {
        Cur_Abbreviation = cur_Abbreviation;
    }

    public String getCur_Scale() {
        return Cur_Scale;
    }

    public void setCur_Scale(String cur_Scale) {
        Cur_Scale = cur_Scale;
    }

    public String getCur_Name() {
        return Cur_Name;
    }

    public void setCur_Name(String cur_Name) {
        Cur_Name = cur_Name;
    }

    public double getCur_OfficialRate() {
        return Cur_OfficialRate;
    }

    public void setCur_OfficialRate(double cur_OfficialRate) {
        Cur_OfficialRate = cur_OfficialRate;
    }
}
