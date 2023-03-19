package com.list.a2do.mvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "my_notes")

public class Note {
    private  String title;
    private String Disc;
    @PrimaryKey(autoGenerate = true)
    int id;

    public Note(String title, String Disc) {
        this.title = title;
       this.Disc = Disc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisc() {
        return Disc;
    }

    public void setDisc(String disc) {
        Disc = disc;
    }




}
