package com.myapplicationdev.android.lp2_quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VER = 1;
	private static final String DATABASE_NAME = "todo.db";

	private static final String TABLE_NAME = "todo";
	private static final String COLUMN_ID = "_id";
	private static final String COLUMN_date = "date";
	private static final String COLUMN_data = "data";


	public DBHelper(Context context) {
		super(context, DATABASE_NAME,null, DATABASE_VER);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableSql = "CREATE TABLE " + TABLE_NAME +  "("
				+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ COLUMN_date + " TEXT,"
				+ COLUMN_data + " TEXT )";
		db.execSQL(createTableSql);
		Log.i("info" ,"created tables");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}



	public ArrayList<TODO> getDetails() {
		ArrayList<TODO> notes = new ArrayList<TODO>();
		String selectQuery = "SELECT " + COLUMN_ID + ", "
				+ COLUMN_date + ", "
				+ COLUMN_data
				+ " FROM " + TABLE_NAME;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String date = cursor.getString(1);
				String data = cursor.getString(2);
				TODO obj = new TODO(id, date, data);
				notes.add(obj);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return notes;
	}


	public String updatetodoDetails(TODO todo) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("update todo set data='" + todo.getData() + "' where _id='" + todo.getId() + "'");
			db.close();

			return "Record updated successfully..";
		}catch (Exception e)
		{
			return "Failed";
		}
	}

	public void insertToDoData(TODO todo) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_date, todo.getDate());
		values.put(COLUMN_data, todo.getData());
		db.insert(TABLE_NAME, null, values);
		db.close();
	}

	public ArrayList<TODO> getTop10Details() {
		ArrayList<TODO> notes = new ArrayList<TODO>();
		String selectQuery = "SELECT " + COLUMN_ID + ", "
				+ COLUMN_date + ", "
				+ COLUMN_data
				+ " FROM " + TABLE_NAME+" LIMIT 10";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String date = cursor.getString(1);
				String data = cursor.getString(2);
				TODO obj = new TODO(id, date, data);
				notes.add(obj);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return notes;

	}

	public String deletetodoDetails(TODO todo) {

		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("delete from todo where _id='"+todo.getId()+"'");
			db.close();

			return "Record deleted successfully..";
		}catch (Exception e)
		{
			return "Failed";
		}
	}
}
