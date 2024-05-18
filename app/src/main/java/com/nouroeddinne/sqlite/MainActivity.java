package com.nouroeddinne.sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Adapter.MyAdapter;
import Controlar.DataBaseHandler;
import Model.Person;

public class MainActivity extends AppCompatActivity {

    LinearLayout linear_get,linear_add,linear_getAll;
    Button button_add,button_get,button_getAll,button_show_one_person_by_id,button_show_one_person_by_name,button_set_one_person;
    EditText editText_search_by_id,editText_search_by_name,editText_set_name,editText_set_age,editText_set_address;
    TextView textView_show_id,textView_show_name,textView_show_age,textView_show_address,textView_number_of_person;
    private RecyclerView recyclerView;
    private List<Person> personList;
    private RecyclerView.Adapter adapter;
    DataBaseHandler db ;
    Context context= this;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new DataBaseHandler(this);

        button_add = findViewById(R.id.button_add);
        button_get = findViewById(R.id.button_get);
        button_getAll = findViewById(R.id.buttongetAll);
        button_show_one_person_by_id = findViewById(R.id.button_update_person);
        button_show_one_person_by_name = findViewById(R.id.button_show_one_person_by_name);
        button_set_one_person = findViewById(R.id.button_set_new_person);

        editText_search_by_id = findViewById(R.id.editText_updateage);
        editText_search_by_name = findViewById(R.id.editText_search_by_name);
        editText_set_name = findViewById(R.id.editText_set_name);
        editText_set_age = findViewById(R.id.editText_set_age);
        editText_set_address = findViewById(R.id.editText_set_address);

        textView_show_id=findViewById(R.id.textView_show_id);
        textView_show_name = findViewById(R.id.textView_show_name);
        textView_show_age = findViewById(R.id.textView_show_age);
        textView_show_address = findViewById(R.id.textView_show_address);
        textView_number_of_person = findViewById(R.id.textView_number_of_person);

        linear_get = findViewById(R.id.linear_get);
        linear_add = findViewById(R.id.linear_add);
        linear_getAll = findViewById(R.id.linear_getAll);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


















        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_get.setVisibility(View.GONE);
                linear_getAll.setVisibility(View.GONE);
                linear_add.setVisibility(View.VISIBLE);
            }
        });

        button_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_get.setVisibility(View.VISIBLE);
                linear_getAll.setVisibility(View.GONE);
                linear_add.setVisibility(View.GONE);
            }
        });

        button_getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear_get.setVisibility(View.GONE);
                linear_getAll.setVisibility(View.VISIBLE);
                linear_add.setVisibility(View.GONE);

                textView_number_of_person.setText(String.valueOf(db.NumPerson()));
                personList = db.getAllPersons();
                adapter = new MyAdapter(context,personList);
                recyclerView.setAdapter(adapter);
            }
        });

        button_show_one_person_by_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_search_by_id.getText().toString().isEmpty()){
                    editText_search_by_id.setError("Enter ID Search");
                }else {
                    int id = Integer.parseInt(editText_search_by_id.getText().toString());
                    Person person = db.getPersonById(id);
                    if (person!=null){
                        textView_show_id.setText(String.valueOf(person.getId()));
                        textView_show_name.setText(String.valueOf(person.getName()));
                        textView_show_age.setText(String.valueOf(person.getAge()));
                        textView_show_address.setText(String.valueOf(person.getAddress()));
                    }else {
                        textView_show_id.setText("Person not found for ID: "+id);
                        textView_show_name.setText("null");
                        textView_show_age.setText("null");
                        textView_show_address.setText("null");
                    }
                }
                editText_search_by_id.setText("");
            }
        });


        button_show_one_person_by_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_search_by_name.getText().toString().isEmpty()){
                    editText_search_by_name.setError("Enter ID Search");
                }else {
                    String Name = editText_search_by_name.getText().toString();
                    Person person = db.getPersonByName(Name);
                    if (person!=null){
                        textView_show_id.setText(String.valueOf(person.getId()));
                        textView_show_name.setText(String.valueOf(person.getName()));
                        textView_show_age.setText(String.valueOf(person.getAge()));
                        textView_show_address.setText(String.valueOf(person.getAddress()));
                    }else {
                        textView_show_id.setText("Person not found for ID: "+Name);
                        textView_show_name.setText("null");
                        textView_show_age.setText("null");
                        textView_show_address.setText("null");
                    }
                }
                editText_search_by_name.setText("");
            }
        });


        button_set_one_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_set_name.getText().toString().isEmpty()){
                    editText_set_name.setError("Enter Your Name");
                }else {
                    if (editText_set_age.getText().toString().isEmpty()){
                        editText_set_age.setError("Enter Your Age");
                    }else {
                        if (editText_set_address.getText().toString().isEmpty()){
                            editText_set_address.setError("Enter Your Address");
                        }else {
                            db.addPerson(new Person(editText_set_name.getText().toString(),editText_set_address.getText().toString(),Integer.parseInt(editText_set_age.getText().toString())));
                            editText_set_name.setText("");
                            editText_set_address.setText("");
                            editText_set_age.setText("");
                            Toast.makeText(MainActivity.this, "Add Sucsess ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });




















    }

    @Override
    protected void onStart() {
        super.onStart();

        textView_number_of_person.setText(String.valueOf(db.NumPerson()));
        personList = db.getAllPersons();
        adapter = new MyAdapter(context,personList);
        recyclerView.setAdapter(adapter);

    }


























}