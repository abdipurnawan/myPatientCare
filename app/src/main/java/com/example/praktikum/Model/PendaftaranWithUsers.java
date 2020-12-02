package com.example.praktikum.Model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class PendaftaranWithUsers {
    @Embedded public Pendaftaran pendaftaran;
    @Relation(
            parentColumn = "id_user",
            entityColumn = "ID",
            entity = User.class
    )
    public List<User> users;
}
