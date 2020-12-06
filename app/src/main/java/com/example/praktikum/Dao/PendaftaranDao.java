package com.example.praktikum.Dao;

import com.example.praktikum.Model.Pendaftaran;
import com.example.praktikum.Model.PendaftaranWithUsers;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PendaftaranDao {
    @Query("SELECT * FROM pendaftarans ORDER BY ID")
    List<PendaftaranWithUsers> loadAllPendaftaranss();

    @Insert
    void insertPendaftaran(Pendaftaran pendaftaran);

    @Query("SELECT * FROM pendaftarans WHERE ID = :sID")
    PendaftaranWithUsers loadPendaftaranById(int sID);

    @Query("SELECT * FROM pendaftarans WHERE status = :status and id_user = :idUser")
    List<PendaftaranWithUsers> loadPendaftaranByStatusPending(String
                                                        status, int idUser);

    @Query("SELECT * FROM pendaftarans WHERE (status = :status1 and id_user = :idUser) or (status = :status2 and id_user = :idUser)")
    List<PendaftaranWithUsers> loadPendaftaranByStatusResponed(String
                                                                      status1, String status2, int idUser);

    @Query("UPDATE pendaftarans set keluhan = :keluhan, penyakit_bawaan = :penyakit, id_poli = :id_poli, tinggi_badan = :tinggi, berat_badan = :berat where ID = :sID")
    void updatePendaftaran(int sID, String keluhan, String penyakit, int id_poli, String tinggi, String berat);

    @Query("DELETE FROM pendaftarans where ID = :id")
    void deletePendaftaran(int id);

    @Query("SELECT id_poli from pendaftarans where ID = :sID")
    int getIDPoli(int sID);


    //ADMIN
    @Query("SELECT * FROM pendaftarans WHERE status = :status")
    List<PendaftaranWithUsers> loadPendaftaranMasuk(String status);

    @Query("UPDATE pendaftarans set tgl_regis = :tglRegis, status = :stat where ID = :sID")
    void acceptRegistrasi(String tglRegis, String stat, int sID);

    @Query("UPDATE pendaftarans set  status = :stat where ID = :sID")
    void refuseRegistrasi(String stat, int sID);

    @Query("SELECT * FROM pendaftarans WHERE status = :status1 or status= :status2")
    List<PendaftaranWithUsers> loadPendaftaranResponed(String status1, String status2);
}
