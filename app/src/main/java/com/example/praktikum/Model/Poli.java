package com.example.praktikum.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "polis")
public class Poli {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "poli")
    private String poli;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPoli() {
        return poli;
    }

    public void setPoli(String poli) {
        this.poli = poli;
    }
}
