package com.list.a2do;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.list.a2do.Activity.DataInsertActivity;
import com.list.a2do.Adapter.RvAdapter;
import com.list.a2do.databinding.ActivityMainBinding;
import com.list.a2do.databinding.NoteLayoutBinding;
import com.list.a2do.mvvm.Note;
import com.list.a2do.mvvm.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    NoteLayoutBinding noteLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
        noteViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NoteViewModel.class);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                intent.putExtra("type", "AddMode");
                startActivityForResult(intent, 1);
            }
        });
        binding.RV.setLayoutManager(new GridLayoutManager(this, 2));
        binding.RV.setHasFixedSize(true);
        RvAdapter adapter = new RvAdapter();
        binding.RV.setAdapter(adapter);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });
        ItemTouchHelper mIth = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        if (direction == ItemTouchHelper.RIGHT) {


                            noteViewModel.delete(adapter.getNote(viewHolder.getAdapterPosition()));
                            Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
                        } else {

                            Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                            intent.putExtra("type", "update");
                            intent.putExtra("title", adapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                            intent.putExtra("disc", adapter.getNote(viewHolder.getAdapterPosition()).getDisc());
                            intent.putExtra("id", adapter.getNote(viewHolder.getAdapterPosition()).getId());
                            startActivityForResult(intent, 2);
                            Toast.makeText(MainActivity.this, "Note Update ", Toast.LENGTH_SHORT).show();

//
                        }
                    }
                });
        mIth.attachToRecyclerView(binding.RV);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            assert data != null;
            String title = data.getStringExtra("title");
            String disc = data.getStringExtra("disc");
            Note note = new Note(title, disc);
            noteViewModel.insert(note);
            Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();
        } else if (requestCode == 2) {
            String title = data.getStringExtra("title");
            String disc = data.getStringExtra("disc");
            Note note = new Note(title, disc);
            note.setId(data.getIntExtra("id", 0));
            noteViewModel.update(note);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

        }



    }

        }