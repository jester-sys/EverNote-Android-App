package com.list.a2do.mvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.list.a2do.mvvm.Note;
import com.list.a2do.mvvm.NoteDao;
import com.list.a2do.mvvm.Note_Database;

import java.util.List;

public class NoteRepo {
    public NoteDao noteDao;
    private LiveData<List<Note>> notelist;

    public NoteRepo (Application application) {
        Note_Database note_database = Note_Database.getInstance(application);
        noteDao = note_database.noteDao();
        notelist = noteDao.AllData();


    }
    public  void  InsertData(Note note){new InsertTask(noteDao).execute(note);}
    public void  updateData(Note note){ new UpdateTask(noteDao).execute(note);}
    public  void  deleteData(Note note){ new DeleteTask(noteDao).execute(note);}
    public  LiveData<List<Note>> AllData(){
        return  notelist;
    }
    private static class InsertTask extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;

        public InsertTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
        private static class UpdateTask extends AsyncTask<Note,Void,Void>{
            NoteDao noteDao;

            public UpdateTask(NoteDao noteDao) {
                this.noteDao = noteDao;
            }

            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.update(notes[0]);
                return null;
            }
        }
        private static class DeleteTask extends AsyncTask<Note,Void,Void>{
            NoteDao noteDao;

            public DeleteTask(NoteDao noteDao) {

                this.noteDao = noteDao;
            }

            @Override
            protected Void doInBackground(Note... notes) {
                noteDao.delete(notes[0]);
                return null;
            }
        }

    }

