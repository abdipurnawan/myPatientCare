package com.example.praktikum.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pendaftarans")
public class Pendaftaran {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "id_user")
    private int id_user;

    @ColumnInfo(name = "poli")
    private String poli;

    @ColumnInfo(name = "keluhan")
    private String keluhan;

    @ColumnInfo(name = "tgl_regis")
    private String tgl_regis;

    @ColumnInfo(name = "penyakit_bawaan")
    private String penyakit_bawaan;

    @ColumnInfo(name = "tinggi_badan")
    private String tinggi_badan;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "berat_badan")
    private String berat_badan;

    //id


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    //id_user
    public int getId_user(){
        return id_user;
    }

    public void setId_user(int id_user){
        this.id_user= id_user;
    }

    //id_poli
    public String getPoli(){
        return poli;
    }

    public void setPoli(String poli){
        this.poli= poli;
    }

    //keluhan
    public String getKeluhan(){
        return keluhan;
    }

    public void setKeluhan(String keluhan){
        this.keluhan = keluhan;
    }

    //tgl_regis
    public String getTgl_regis(){
        return tgl_regis;
    }

    public void setTgl_regis(String tgl_regis){
        this.tgl_regis = tgl_regis;
    }

    //penyakit
    public String getPenyakit_bawaan(){
        return penyakit_bawaan;
    }

    public void setPenyakit_bawaan(String penyakit_bawaan){
        this.penyakit_bawaan = penyakit_bawaan;
    }

    //tinggibadan
    public String getTinggi_badan(){
        return tinggi_badan;
    }

    public void setTinggi_badan(String tinggi_badan){
        this.tinggi_badan = tinggi_badan;
    }

    //status
    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    //berat
    public String getBerat_badan(){
        return berat_badan;
    }

    public void setBerat_badan(String berat_badan){
        this.berat_badan = berat_badan;
    }
}

