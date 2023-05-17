package com.example.a8_1c.data;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.UiContext;

import com.example.a8_1c.model.User;
import com.example.a8_1c.model.UserPlaylist;
import com.example.a8_1c.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" + Util.USER_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + Util.USER_NAME + " TEXT,"
                + Util.PASSWORD + " TEXT, " + Util.FULL_NAME + " TEXT )" ;

        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        String CREATE_PLAYLIST_TABLE = "CREATE TABLE " + Util.PLAYLIST_TABLE_NAME + "(" + Util.PLAYLIST_ITEM_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + Util.USER_ID + " INT,"
                + Util.URLSTR + " TEXT )" ;

        sqLiteDatabase.execSQL(CREATE_PLAYLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS '" + Util.TABLE_NAME + "'";
        sqLiteDatabase.execSQL(DROP_USER_TABLE);

        String PLAYLIST_TABLE_NAME = "DROP TABLE IF EXISTS'" + Util.PLAYLIST_TABLE_NAME + "'";
        sqLiteDatabase.execSQL(PLAYLIST_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Util.USER_NAME, user.getUsername());
        contentValues.put(Util.PASSWORD, user.getPassword());
        contentValues.put(Util.FULL_NAME, user.getFull_name());

        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    @SuppressLint("Range")
    public User fetchUser (String userName, String password)
    {
        User retUser = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.USER_ID}, Util.USER_NAME + " =? and "
        //        + Util.PASSWORD + "=?", new String[] {userName, password}, null, null, null);

        String query = "SELECT * FROM " + Util.TABLE_NAME + " WHERE " + Util.USER_NAME + " = '" + userName
            + "' and " + Util.PASSWORD + " = '" + password + "'";
       // Log.d("**************", query);
        Cursor cursor = db.rawQuery(query, null);
        int numberOfrows = cursor.getCount();
        if (numberOfrows > 0) {
            cursor.moveToFirst();
            retUser.setUser_id(cursor.getInt(cursor.getColumnIndex(Util.USER_ID)));
        }
        db.close();
        return retUser;
    }


    public long insertPl(int userId, String urlstr)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(Util.USER_ID, userId);
        contentValues.put(Util.URLSTR, urlstr);

        long newRowId = db.insert(Util.PLAYLIST_TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    @SuppressLint("Range")
    public List<UserPlaylist> fetchPl (int userId)
    {
        List<UserPlaylist> myList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Util.PLAYLIST_TABLE_NAME + " WHERE " + Util.USER_ID + " = " + userId;
        Cursor cursor = db.rawQuery(query, null);
        int numberOfrows = cursor.getCount();
        cursor.moveToFirst();
        for (int i=0; i < numberOfrows; i++)
        {
           // Log.d("Here", cursor.getString(cursor.getColumnIndex(Util.URLSTR)).toString());
            UserPlaylist plitem = new UserPlaylist();
            plitem.setPlaylist_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.PLAYLIST_ITEM_ID))));
            plitem.setUser_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.USER_ID))));
            plitem.setUrl(cursor.getString(cursor.getColumnIndex(Util.URLSTR)));
            myList.add(plitem);

            cursor.moveToNext();
        }
        db.close();
        return myList;
    }
}
