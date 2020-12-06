package com.example.praktikum.Model;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class PendaftaranWithPolis {
        @Embedded public Pendaftaran pendaftaran;
        @Relation(
                parentColumn = "id_poli",
                entityColumn = "ID",
                entity = Poli.class
        )
        public List<Poli> polis;
}
