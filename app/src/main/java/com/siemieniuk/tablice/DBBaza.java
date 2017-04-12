package com.siemieniuk.tablice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 2017-04-12.
 */

public class DBBaza extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "zse";

    // Contacts table name
    private static final String TABLE_ZAJECIA = "zajecia";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAZWA = "nazwa";

    public static int klasyk;
    public static int powter;

    public DBBaza(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ZAJECIA + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAZWA + " TEXT" + ") ";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ZAJECIA);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addAlarm(cstrike contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAZWA, contact.getNazwa()); // Contact Name

        klasyk = contact.getID();

        // Inserting Row
        db.insert(TABLE_ZAJECIA, null, values);
//        db.close(); // Closing database connection
    }



    // Getting single contact
    cstrike getAlarm(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ZAJECIA, new String[] { KEY_ID,
                        KEY_NAZWA, }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        cstrike contact = new cstrike(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<cstrike> getAllAlarm() {
        List<cstrike> contactList = new ArrayList<cstrike>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ZAJECIA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                cstrike contact = new cstrike();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setNazwa(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }



    // Updating single contact
    public int updateContact(cstrike contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAZWA, contact.getNazwa());

        // updating row
        return db.update(TABLE_ZAJECIA, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Deleting single contact
    public void deleteAlarm(cstrike contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ZAJECIA, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

    public void deleteAll()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(TABLE_ZAJECIA, null, null);

    }

    // Getting contacts Count
    public int getAlarmCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ZAJECIA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int getLastAlarmID() {
        int lastId = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String queryty = "SELECT "+ KEY_ID +" FROM "+ TABLE_ZAJECIA + " order by " + KEY_ID + " DESC limit 1";
        Cursor c = db.rawQuery(queryty, null);
        if (c != null && c.moveToFirst()) {
            lastId = (int) c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
            c.close();
        }
        return lastId;
    }

}