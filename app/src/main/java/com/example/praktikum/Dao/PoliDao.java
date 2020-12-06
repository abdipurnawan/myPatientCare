package com.example.praktikum.Dao;

import com.example.praktikum.Model.Poli;
import com.example.praktikum.Model.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PoliDao {

    @Insert(onConflict = REPLACE)
    void insert(Poli poli);

    @Query("SELECT *FROM polis")
    List<Poli> getAllPoli();

    @Query("DELETE FROM polis where ID = :id")
    void deletePoli(int id);

    @Query("UPDATE polis set poli = :namePoli WHERE ID = :sID")
    void updatePoli(int sID, String namePoli);

    @Query("SELECT poli FROM polis WHERE ID = :sID")
    String getPoli(int sID);

}
