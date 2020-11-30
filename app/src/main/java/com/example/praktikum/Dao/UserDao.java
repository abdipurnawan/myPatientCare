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
}
