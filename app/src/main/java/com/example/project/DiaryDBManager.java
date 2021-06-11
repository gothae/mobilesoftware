package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDBManager extends SQLiteOpenHelper {
    static final String DIARY_DB = "Diarys.db";
    static final String DIARY_TABLE = "Diarys";
    Context context = null;
    private static DiaryDBManager dbManager = null;
    static final String CREATE_DB = " CREATE TABLE " +
            DIARY_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " name TEXT NOT NULL, description TEXT NOT NULL," +
            "latitude TEXT NOT NULL, longitude TEXT NOT NULL, image TEXT NOT NULL);";

    public DiaryDBManager(Context context, String dbName,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.context = context;
}

    public static DiaryDBManager getInstance(Context context) {
        if(dbManager == null) {
            dbManager = new DiaryDBManager(context, DIARY_DB,
                    null, 1);
        }
        return dbManager;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int
            newV) {
    }
    public long insert(ContentValues addValue) {
        return getWritableDatabase().insert(DIARY_TABLE,
                null, addValue);
    }
    public Cursor query(String [] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy){
        return getReadableDatabase().query(DIARY_TABLE,
                columns, selection, selectionArgs, groupBy, having, orderBy);
    }
    public int delete(String whereClause, String[] whereArgs)
    {
        return getWritableDatabase().delete(DIARY_TABLE,
                whereClause, whereArgs);
    }
}

