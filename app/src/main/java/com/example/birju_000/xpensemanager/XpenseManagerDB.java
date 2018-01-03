/**
 * Created by Bpatel0967 on 10/12/2017.
 */
package com.example.birju_000.xpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public final class XpenseManagerDB {

    //Private constructor
    //private XpenseManagerDB(){};

    //Databse constatnts
    public static final String DB_NAME = "XpenseManager.db";
    public static final int DB_VERSION = 1;


    //user table constants
    public static final String USER_TABLE = "user";

    public static final String USER_ID = "_id";
    public static final int USER_ID_COL = 0;

    public static final String FIRST_NAME = "firstName";
    public static final int FIRST_NAME_COL = 1;

    public static final String LAST_NAME = "lastName";
    public static final int LAST_NAME_COL = 2;

    public static final String EMAIL = "email";
    public static final int EMAIL_COL = 3;

    public static final String PASSWORD = "password";
    public static final int PASSWORD_COL = 4;

    //expense table constants
    public static final String EXPENSE_TABLE = "expense";

    public static final String EXPENSE_ID = "_id";
    public static final int EXPENSE_ID_COL = 0;

    public static final String DATE = "date";
    public static final int DATE_COL = 1;

    public static final String AMOUNT = "amount";
    public static final int AMOUNT_COL = 2;

    public static final String LOCATION = "location";
    public static final int LOCATION_COL = 3;

    public static final String DESCRIPTION = "description";
    public static final int DESCRIPTION_COL = 4;

    public static final String CATEGORY = "category";
    public static final int CATEGORY_COL = 5;

    //category table constatnts
    public  static final String CATEGORY_TABLE = "category";

    public  static  final  String CATEGORY_ID = "_id";
    public static final int CATEGORY_ID_COL = 0;

    public  static  final  String NAME = "name";
    public static final int NAME_COL = 0;


    //Create and drop table statements
    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + USER_TABLE + "(" +
                    USER_ID + " INTEGER PRIMARY KEY," +
                    FIRST_NAME + " TEXT," +
                    LAST_NAME + " TEXT," +
                    EMAIL + " TEXT," +
                    PASSWORD + " TEXT);";

    private static final String CREATE_EXPENSE_TABLE =
            "CREATE TABLE " + EXPENSE_TABLE + "(" +
                    EXPENSE_ID + " INTEGER PRIMARY KEY," +
                    DATE + " TEXT," +
                    AMOUNT + " TEXT," +
                    LOCATION + " TEXT," +
                    DESCRIPTION + " TEXT," +
                    CATEGORY + " TEXT);";

    private static final String CREATE_CATEGORY_TABLE =
            "CREATE TABLE " + CATEGORY_TABLE + "(" +
                    CATEGORY_ID + " INTEGER PRIMARY KEY," +
                    NAME + " TEXT);";



    private static  final String DELETE_USER_TABLE =
            "DROP TABLE IF EXISTS " + USER_TABLE;
    private static final String DELTE_EXPENSE_TABLE =
            "DROP TABLE IF EXISTS " + EXPENSE_TABLE;
    private static  final String DELETE_CATEGORY_TABLE =
            "DROP TABLE IF EXISTS " + CATEGORY_TABLE;


    //Using SQliteOpenHelper to create the database

    public static class UserDbHelper extends SQLiteOpenHelper {



        public UserDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_EXPENSE_TABLE);
            db.execSQL(CREATE_CATEGORY_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DELETE_USER_TABLE);
            db.execSQL(DELTE_EXPENSE_TABLE);
            db.execSQL(DELETE_CATEGORY_TABLE);
            onCreate(db);
        }
    }

    private SQLiteDatabase db;
    private UserDbHelper dbHelper;

    //constructor
    public XpenseManagerDB(Context context){
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

    //USER TABLE CRUD METHODS
    public long insertUser(ContentValues values){
        this.openWriteableDB();
        long rowID = db.insert(USER_TABLE, null, values);
        this.closeDB();

        return rowID;
    }

    public User getUser(String email)
    {
        String where = EMAIL + "=?";
        String[] whereArgs = {email};

        this.openReadableDB();
        Cursor cursor = db.query(USER_TABLE, null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        User user = getUserFromCursor(cursor);
        if(cursor != null){
            cursor.close();
        }
        this.closeDB();
        return  user;
    }


    private static User getUserFromCursor(Cursor cursor)
    {
        if(cursor == null || cursor.getCount() == 0){
            return  null;
        }
        else{
            try{
                User user = new User(
                        cursor.getInt(USER_ID_COL),
                        cursor.getString(FIRST_NAME_COL),
                        cursor.getString(LAST_NAME_COL),
                        cursor.getString(EMAIL_COL),
                        cursor.getString(PASSWORD_COL)
                );
                return user;
            }
            catch (Exception e){
                return  null;
            }
        }
    }

    public int deleteUser(long id)
    {
        String where = USER_ID + "=?";
        String[] whereArgs = {String.valueOf(id)};

        this.openWriteableDB();
        int rowCount = db.delete(USER_TABLE, where, whereArgs);
        this.closeDB();

        return  rowCount;
    }

    //EXPENSE TABLE CRUD METHODS
    public long insertExpense(ContentValues values){
        this.openWriteableDB();
        long rowID = db.insert(EXPENSE_TABLE, null, values);
        this.closeDB();

        return rowID;
    }

    public Expense getExpense(int id)
    {
        String where = EXPENSE_ID + "?";
        String[] whereArgs = {String.valueOf(id)};

        this.openReadableDB();
        Cursor cursor = db.query(EXPENSE_TABLE, null, where, whereArgs, null, null, null);
        cursor.moveToFirst();

        Expense expense = getExpenseFromCurosr(cursor);
        if(cursor != null)
        {
            cursor.close();
        }
        this.closeDB();
        return expense;
    }

    private static Expense getExpenseFromCurosr(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        } else {
            try {
                Expense expense = new Expense(
                        cursor.getString(DATE_COL),
                        cursor.getDouble(AMOUNT_COL),
                        cursor.getString(LOCATION_COL),
                        cursor.getString(DESCRIPTION_COL),
                        cursor.getString(CATEGORY_COL)
                );
                return expense;
            } catch (Exception e) {
                return null;
            }
        }
    }

    public   List<Expense> getExpenses(){
        List<Expense> expenses = new ArrayList<>();

        String query = "SELECT * FROM " + EXPENSE_TABLE;
        this.openWriteableDB();
        Cursor cursor = db.rawQuery(query, null);

        //looping through all rows
        if(cursor.moveToFirst()){
            do{
                Expense expense = new Expense();
                expense.setExpenseId(Integer.parseInt(cursor.getString(0)));
                expense.setDate(cursor.getString(1));
                expense.setAmount(Double.parseDouble(cursor.getString(2)));
                expense.setLocation(cursor.getString(3));
                expense.setDescription(cursor.getString(4));
                expense.setCategory(cursor.getString(5));

                expenses.add(expense);
            }while (cursor.moveToNext());
        }
        return  expenses;
    }

    public Cursor getAllExpenses(){
        String query = "SELECT * FROM " + EXPENSE_TABLE;
        this.openWriteableDB();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return  cursor;
    }

    public double getTotalExpenses(){
        double total = 0f;
        String query = "SELECT * FROM " + EXPENSE_TABLE;
        this.openWriteableDB();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do
            {
                total += (Double.parseDouble(cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return total;
    }
}

