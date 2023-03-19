package com.list.a2do.mvvm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Note.class,version = 1)
public abstract class Note_Database extends RoomDatabase {
    public abstract NoteDao noteDao();
    private static Note_Database instance;
    public static synchronized Note_Database getInstance(Context context ){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext()
                            ,Note_Database.class,"MyNote_database").fallbackToDestructiveMigration()
                    .build();

        }
        return instance;
    }

}
