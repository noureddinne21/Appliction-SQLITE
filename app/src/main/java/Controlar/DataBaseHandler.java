package Controlar;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Person;
import Utel.Utels;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(@Nullable Context context) {
        super(context,Utels.DATABASE_NAME,null, Utels.DATABASE_VERTION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREAT_T1_TABLE="CREATE TABLE "+Utels.TABLE_NAME+" ("+
                Utels.KEY_ID+" INTEGER PRIMARY KEY,"+
                Utels.KEY_NAME+" TEXT,"+
                Utels.KEY_ADDRESS+ " TEXT,"+
                Utels.KEY_AGE+" INTEGER)";
        db.execSQL(CREAT_T1_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utels.TABLE_NAME);
        onCreate(db);
    }

    public void addPerson(Person person){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utels.KEY_NAME, person.getName());
        contentValues.put(Utels.KEY_ADDRESS, person.getAddress());
        contentValues.put(Utels.KEY_AGE, String.valueOf(person.getAge()));
        sqLiteDatabase.insert(Utels.TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
    }






    public Person getPersonById(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Utels.TABLE_NAME,
                new String[]{Utels.KEY_ID, Utels.KEY_NAME, Utels.KEY_ADDRESS, Utels.KEY_AGE}, Utels.KEY_ID+"=?",
                new String[]{String.valueOf(id)},
                null,null,null,null);


        if (cursor != null && cursor.moveToFirst()) {
            Person person = new Person(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(3)));
            cursor.close(); // Close cursor when done with it
            return person;
        }
        // Close cursor even if it's null
        if (cursor != null) {
            cursor.close();
        }
        return null; // Return null if person not found
    }



    public List<Person> getAllPersons(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Person> personList = new ArrayList<Person>();
        String getAll = "SELECT * FROM "+Utels.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);

        if (cursor.moveToFirst())
            do {
                Person person = new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setName(cursor.getString(1));
                person.setAddress(cursor.getString(2));
                person.setAge(Integer.parseInt(cursor.getString(3)));
                personList.add(person);
            }while (cursor.moveToNext());

        return personList;

    }



    public Person getPersonByName(String Name){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Utels.TABLE_NAME,
                new String[]{Utels.KEY_ID, Utels.KEY_NAME, Utels.KEY_ADDRESS, Utels.KEY_AGE}, Utels.KEY_NAME+"=?",
                new String[]{Name},
                null,null,null,null);


        if (cursor != null && cursor.moveToFirst()) {
            Person person = new Person(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    Integer.parseInt(cursor.getString(3)));
            cursor.close(); // Close cursor when done with it
            return person;
        }
        // Close cursor even if it's null
        if (cursor != null) {
            cursor.close();
        }
        return null; // Return null if person not found
    }


    public int updatePerson(Person person){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utels.KEY_NAME, person.getName());
        contentValues.put(Utels.KEY_ADDRESS, person.getAddress());
        contentValues.put(Utels.KEY_AGE, String.valueOf(person.getAge()));
        int result = sqLiteDatabase.update(Utels.TABLE_NAME,contentValues,Utels.KEY_ID+"=?",new String[]{String.valueOf(person.getId())});
        sqLiteDatabase.close();
        return result;
    }


    public int deletePerson(Person person){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete(Utels.TABLE_NAME,Utels.KEY_ID+"=?",new String[]{String.valueOf(person.getId())});
        sqLiteDatabase.close();
        return result;
    }


    public int NumPerson(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String getAll = "SELECT * FROM "+Utels.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);
        return cursor.getCount();
    }


















}

