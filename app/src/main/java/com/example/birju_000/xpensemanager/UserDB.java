/**
 * Created by Bpatel0967 on 10/12/2017.
 */
package com.example.birju_000.xpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class UserDB {

    //Private constructor
    //private UserDB(){};

    //Databse constatnts
    public static final String DB_NAME = "XpenseManager.db";
    public static final int DB_VERSION = 1;

  /*  //Inner class that defines table contents
    public  static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "User";
        public static final String FIRST_NAME = "FirstName";
        public static final String LAST_NAME = "LastName";
        public static final String EMAIL = "Email";
        public static final String PASSWORD = "Password";
    }*/

    public static final String USER_TABLE = "user";
    public static final String USER_ID = "_id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";



    //Create and drop table statement
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + USER_TABLE + "(" +
                    USER_ID + " INTEGER PRIMARY KEY," +
                    FIRST_NAME + " TEXT," +
                    LAST_NAME + " TEXT," +
                    EMAIL + " TEXT," +
                    PASSWORD + " TEXT);";

    private static  final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + USER_TABLE;


    //Usig SQliteOpenHelper to create the database

    public static class UserDbHelper extends SQLiteOpenHelper {



        public UserDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_TABLE);
            onCreate(db);
        }
    }

    private SQLiteDatabase db;
    private UserDbHelper dbHelper;

    //constructor
    public UserDB(Context context){
        dbHelper = new UserDbHelper(context, DB_NAME, null, DB_VERSION);
    }

    //private methods
    private void openReadableDB(){
        db = dbHelper.getReadableDatabase();
    }
    private void openWriteableDB(){
        db = dbHelper.getWritableDatabase();
    }
    private void closeDB(){
        if(db != null){
            db.close();
        }
    }

    public long insertUser(ContentValues values){
        this.openWriteableDB();
        long rowID = db.insert(USER_TABLE, null, values);
        this.closeDB();

        return rowID;
    }



}

