package com.example.contactappe020320099;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constants.TABLE_NAME);

        onCreate(db);
    }

    public long insertContact(String image,String name,String phone,String email,String note,String addedTime,String updatedTime){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.C_IMAGE,image);
        contentValues.put(Constants.C_NAME,name);
        contentValues.put(Constants.C_PHONE,phone);
        contentValues.put(Constants.C_EMAIL,email);
        contentValues.put(Constants.C_NOTE,note);
        contentValues.put(Constants.C_ADDED_TIME,addedTime);
        contentValues.put(Constants.C_UPDATED_TIME,updatedTime);

        long id = db.insert(Constants.TABLE_NAME,null,contentValues);

        db.close();

        return id;
    }

    public void updateContact(String id,String image,String name,String phone,String email,String note,String addedTime,String updatedTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constants.C_IMAGE,image);
        contentValues.put(Constants.C_NAME,name);
        contentValues.put(Constants.C_PHONE,phone);
        contentValues.put(Constants.C_EMAIL,email);
        contentValues.put(Constants.C_NOTE,note);
        contentValues.put(Constants.C_ADDED_TIME,addedTime);
        contentValues.put(Constants.C_UPDATED_TIME,updatedTime);

        db.update(Constants.TABLE_NAME,contentValues,Constants.C_ID+" =? ",new String[]{id} );
        db.close();
    }

    public void deleteContact(String id){
        SQLiteDatabase db =  getWritableDatabase();

        db.delete(Constants.TABLE_NAME,Constants.C_ID +" =? ",new String[]{id});
        db.close();
    }

    public void deleteAllContact(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM "+Constants.TABLE_NAME);
        db.close();
    }


    public ArrayList<ModelContact> getAllData(String orderBy){
        ArrayList<ModelContact> arrayList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                ModelContact modelContact = new ModelContact(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PHONE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_EMAIL)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NOTE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_ADDED_TIME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_UPDATED_TIME))
                );
                arrayList.add(modelContact);
            }while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    public ArrayList<ModelContact> getSearchContact(String query){
        ArrayList<ModelContact> contactList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String queryToSearch = "SELECT * FROM "+Constants.TABLE_NAME+" WHERE "+Constants.C_NAME + " LIKE '%" +query+"%'";
        Cursor cursor = db.rawQuery(queryToSearch,null);

        if (cursor.moveToFirst()){
            do {
                ModelContact modelContact = new ModelContact(
                        ""+cursor.getInt(cursor.getColumnIndexOrThrow(Constants.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NAME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_IMAGE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_PHONE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_EMAIL)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_NOTE)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_ADDED_TIME)),
                        ""+cursor.getString(cursor.getColumnIndexOrThrow(Constants.C_UPDATED_TIME))
                );
                contactList.add(modelContact);
            }while (cursor.moveToNext());
        }
        db.close();
        return contactList;
    }
}