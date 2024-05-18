package com.nouroeddinne.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Controlar.DataBaseHandler;
import Model.Person;

public class ShowandUpdateActivity extends AppCompatActivity {

    Button button_update,button_delete;
    EditText editText_id,editText_name,editText_age,editText_address;
    DataBaseHandler db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_showand_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button_update = findViewById(R.id.button_update_person);
        button_delete = findViewById(R.id.button_delete);
        editText_id = findViewById(R.id.editText_update_id);
        editText_name = findViewById(R.id.editText_update_name);
        editText_age = findViewById(R.id.editText_update_age);
        editText_address = findViewById(R.id.editText_update_address);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editText_id.setText(extras.getString("id"));
            editText_name.setText(extras.getString("name"));
            editText_age.setText(extras.getString("age"));
            editText_address.setText(extras.getString("address"));
        }
        db = new DataBaseHandler(this);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person(Integer.parseInt(
                        editText_id.getText().toString()),
                        String.valueOf(editText_name.getText()),
                        String.valueOf(editText_address.getText()),
                        Integer.parseInt(editText_age.getText().toString()));
                db.updatePerson(person);

                Intent intent = new Intent(ShowandUpdateActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person(Integer.parseInt(
                        editText_id.getText().toString()),
                        String.valueOf(editText_name.getText()),
                        String.valueOf(editText_address.getText()),
                        Integer.parseInt(editText_age.getText().toString()));
                db.deletePerson(person);

                Intent intent = new Intent(ShowandUpdateActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }














}