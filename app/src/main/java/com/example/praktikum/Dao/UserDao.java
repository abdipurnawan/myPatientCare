package com.example.praktikum.Dao;

import com.example.praktikum.Model.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {
    @Insert(onConflict = REPLACE)
    void insert(User user);

    //delete
    @Delete
    void delete(User user);

    //deleteall
    @Delete
    void reset(List<User> users);

    //login
    @Query("Select *from users where email = :email and password = :password")
    User loginUser(String email, String password);

    //cek email unique
    @Query("Select *from users where email = :email limit 1")
    User cekEmail(String email);

    //editUser
    @Query("UPDATE users set name = :nameUser, email = :emailUser, mobile = :mobileUser, gender = :genderUser, address = :addressUser, birthdate = :birthdateUser WHERE ID = :sID")
    void updateUser(int sID, String nameUser, String emailUser, String mobileUser, String genderUser, String addressUser, String birthdateUser);

    //get spesifik user
    @Query("SELECT *from users where ID = :sID")
    User getUser(int sID);

    //password change
    @Query("UPDATE users set password = :newPass where ID = :sID")
    void setNewPassword(int sID, String newPass);
}
