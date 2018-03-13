package com.example.uday.vero;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by uday on 1/24/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME="contactSignup.db";
    private static final String TABLE_NAME="contactsign_table";
    private static final String COLOUMN_ID="id";
    private static final String COLOUMN_EMAIL="email";
    private static final String COLOUMN_FNAME="fname";
    private static final String COLOUMN_LNAME="lname";
    private static final String COLOUMN_PASSWORD="password";
    SQLiteDatabase sqLiteDatabase;

    private static final String TABLE_CREATE="create table contactsign_table (id integer primary key not null ," +
            "email text not null,fname text not null,lname text not null,password text not null);";

    public DatabaseHelper(Context context)
    { //DatabaseHelper constructor
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
        this.sqLiteDatabase=sqLiteDatabase;
    }

    public void insertContact(Contact cs){
        sqLiteDatabase=this.getWritableDatabase();
        //retrieving the ContactClass values
         ContentValues values=new ContentValues();
        //ID is the count of entries in the table database
         String query= "select * from contactsign_table";
         Cursor cursor=sqLiteDatabase.rawQuery(query,null);
         int count=cursor.getCount();

         values.put(COLOUMN_ID,count);
         values.put(COLOUMN_EMAIL, cs.getEmail());
         values.put(COLOUMN_FNAME,cs.getFname());
         values.put(COLOUMN_LNAME,cs.getLname());
         values.put(COLOUMN_PASSWORD,cs.getPassword());
         sqLiteDatabase.insert(TABLE_NAME, null, values);
         sqLiteDatabase.close();
    }
    public String searchpassword(String email) {
        sqLiteDatabase = this.getReadableDatabase();
        String query = "select email and password from " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(email)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String query = "Drop Table if exists" + TABLE_NAME;
    sqLiteDatabase.execSQL(query);
    this.onCreate(sqLiteDatabase);
    }
}
