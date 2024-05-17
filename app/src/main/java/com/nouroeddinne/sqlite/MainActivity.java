package com.nouroeddinne.sqlite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import Controlar.DataBaseHandler;
import Model.Person;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button_add,button_get,button_getAll,button_show_one_person;
    EditText editText_search ;
    TextView textView_show_id,textView_show_name,textView_show_age,textView_show_address;
    DataBaseHandler db ;
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
        button_show_one_person = findViewById(R.id.button_show_one_person);

        editText_search = findViewById(R.id.editText_search);
        textView_show_id=findViewById(R.id.textView_show_id);
        textView_show_name = findViewById(R.id.textView_show_name);
        textView_show_age = findViewById(R.id.textView_show_age);
        textView_show_address = findViewById(R.id.textView_show_address);



        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        button_show_one_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_search.getText().toString().isEmpty()){

                    editText_search.setError("Enter ID Search");

                }else {

                    int id = Integer.parseInt(editText_search.getText().toString());
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
            }
        });



        //db.addPerson(new Person("nouro","SK21",22));
        //db.addPerson(new Person("NOki","batna",12));
        //db.addPerson(new Person("LOLO","usa",24));
        //db.addPerson(new Person("mohamed","uk",32));
        //db.addPerson(new Person("java","doubai",44));


//        List<Person> personList = db.getAllPersons();
//        for (Person p : personList){
//            String allData = "ID : "+p.getId()+" Name : "+p.getName()+" Address : "+p.getAddress()+" Age :"+p.getAge();
//            Log.d("allPerson", allData);
//        }


















    }
}