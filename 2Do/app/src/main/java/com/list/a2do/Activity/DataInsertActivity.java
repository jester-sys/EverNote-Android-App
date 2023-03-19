package com.list.a2do.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.list.a2do.MainActivity;
import com.list.a2do.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {
    ActivityDataInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());





        String type = getIntent().getStringExtra("type");


        if (type.equals("update")) {
            setTitle("Update");

                binding.Title.setText(getIntent().getStringExtra("title"));
                binding.Disc.setText(getIntent().getStringExtra("disc"));
                int id = getIntent().getIntExtra("id", 0);
            binding.Add.setText("Update");

                binding.Add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent();
                        intent.putExtra("title", binding.Title.getText().toString());
                        intent.putExtra("disc", binding.Disc.getText().toString());
                        intent.putExtra("id", id);

                        setResult(RESULT_OK, intent);
                        finish();


                    }
                });



        } else {
            setTitle("Add Mode");
            binding.Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.putExtra("title", binding.Title.getText().toString());
                    intent.putExtra("disc", binding.Disc.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();


                }

            });

        }


    }
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(  new Intent( DataInsertActivity.this, MainActivity.class));

    }

}