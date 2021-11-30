package com.ivanborodkin.entity;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class Contract {

    private int id;

    private int number;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfConclusion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfLastUpdate;

    private boolean checkBox;

    public Contract(int id, int number, Date dateOfConclusion, Date dateOfLastUpdate, boolean checkBox) {
        this.id = id;
        this.number = number;
        this.dateOfConclusion = dateOfConclusion;
        this.dateOfLastUpdate = dateOfLastUpdate;
        this.checkBox = checkBox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfConclusion() {
        return dateOfConclusion;
    }

    public void setDateOfConclusion(Date dateOfConclusion) {
        this.dateOfConclusion = dateOfConclusion;
    }

    public Date getDateOfLastUpdate() {
        return dateOfLastUpdate;
    }

    public void setDateOfLastUpdate(Date dateOfLastUpdate) {
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }

}
