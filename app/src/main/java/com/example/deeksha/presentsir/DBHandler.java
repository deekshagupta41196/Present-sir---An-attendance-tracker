package com.example.deeksha.presentsir;

/**
 * Created by Deeksha on 7/6/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler
{
    public static final String DB_SID = "sid";
    public static final String DB_SNAME = "sname";
    public static final String DB_USERNAME = "username";
    public static final String DB_PASSWORD = "password";
    public static final String DB_BRANCH = "branch";
    public static final String DB_SEMESTER = "semester";
    public static final String DB_SECTION = "section";
    public static final String DB_ATTENDANCE = "attendance";
    public static final String DB_DT = "dt";

    public static final String DB_NAME = "Attendancetracker";
    public static final String DB_STDTABLE = "Studentlogininfo";
    public static final String DB_ATTETABLE = "AttendanceSheetdetails";
    public static final String DB_DAY="day";

    public static final int DB_VERSION = 1;
    public static final String DB_CREATE = "CREATE TABLE "+DB_STDTABLE+ " (" +DB_SID+ " TEXT PRIMARY KEY ,  "+DB_SNAME+" TEXT, "+DB_USERNAME+" TEXT, "+DB_PASSWORD+" TEXT, "+DB_BRANCH+" TEXT, "+DB_SEMESTER+" INTEGER, "+DB_SECTION+" TEXT)";
    public static final String DB_ACREATE = "CREATE TABLE "+DB_ATTETABLE+" ("+DB_SID+" TEXT, "+DB_ATTENDANCE+" TEXT , "+DB_DT+" TEXT, "+DB_DAY+" INTEGER, FOREIGN KEY ("+DB_SID+") REFERENCES "+DB_STDTABLE+"("+DB_SID+"))";
    public SQLiteDatabase db;
    public SQLHelper helper;
    public Context context;



    public DBHandler(Context context)
    {
        this.context = context;
        helper = new SQLHelper();
        db = helper.getWritableDatabase();
    }
    public DBHandler openReadable() throws android.database.sqlite.SQLiteException
    {
        helper=new SQLHelper();
        db=helper.getReadableDatabase();
        return this;
    }

    public Cursor checkid(String sid)
    {
        String [] column={DB_SID};
        Cursor cursor=db.query(DB_STDTABLE,column,DB_SID+"='"+sid+"'",null,null,null,null);
        return cursor;
    }
    public Cursor list(String branch, int semester, String section)
    {
        String query = "select "+DB_SID+","+DB_SNAME+" from "+DB_STDTABLE+" where "+DB_BRANCH+" = '"+branch+"' and "+DB_SEMESTER+" = " +semester+ " and "+DB_SECTION+" = '"+section+"'order by "+DB_SNAME;
        Cursor cursor=db.rawQuery(query,null);
        ArrayList<Student> al=new ArrayList<>();
        cursor.moveToFirst();
        return cursor;
    }
    public Cursor deletestudentt(String sid)
    {
        String query="delete from "+DB_ATTETABLE+" where "+DB_SID+" = '"+sid+"'";
        Log.d("query11", "deletestudentt: "+query);
        return db.rawQuery(query,null);
    }


    public Cursor deletestudent(String sid)
    {
        String query="delete from "+DB_STDTABLE+" where "+DB_SID+" = '"+sid+"'";
        Log.d("query11", "deletestudent: "+query);
        return db.rawQuery(query,null);
    }

    public Cursor login(String user,String pass)
    {
        String [] column={DB_USERNAME,DB_PASSWORD};
        Cursor cursor=db.query(DB_STDTABLE,column,DB_USERNAME+"='"+user+"' and "+DB_PASSWORD+"='"+pass+"'",null,null,null,null);
        return cursor;
    }
    public void close()
    {
        db.close();
    }

    public long addstudent(String sid, String sname,String username, String password, String branch,int semester, String section) {
        ContentValues cv = new ContentValues();
        cv.put("sid", sid);
        cv.put("sname", sname);
        cv.put("username", username);
        cv.put("password", password);
        cv.put("branch", branch);
        cv.put("semester", semester);
        cv.put("section",section);
        return db.insert(DB_STDTABLE, null, cv);
    }

    public Cursor retrieveall(String branch,String semester,String section)
    {
        String query="Select "+DB_SID+","+DB_SNAME+",count("+DB_ATTENDANCE+") from "+DB_STDTABLE+" natural join "+DB_ATTETABLE+" where "+DB_BRANCH+"='"+branch+"' and "+DB_SEMESTER+"='"+semester+"' and "+DB_SECTION+"='"+section+"' and "+DB_ATTENDANCE+"='P' group by "+DB_SID;
        return db.rawQuery(query,null);
    }
    public Cursor retrieveday(String branch,String semester,String section)
    {
        String query="Select sum("+DB_DAY+") from "+DB_STDTABLE+" natural join "+DB_ATTETABLE+" where "+DB_BRANCH+"='"+branch+"' and "+DB_SEMESTER+"='"+semester+"' and "+DB_SECTION+"='"+section+"' group by "+DB_SID;
        return db.rawQuery(query,null);
    }
    public long addattendane(String sid,String attendance,String date,int day)
    {
        ContentValues cv=new ContentValues();
        cv.put(DB_SID,sid);
        cv.put(DB_ATTENDANCE,attendance);
        cv.put(DB_DT,date);
        cv.put(DB_DAY,day);
        Log.d("content", "addattendane: "+cv);
        return db.insert(DB_ATTETABLE,null,cv);
    }

    public  Cursor retrieveinfo(String email,String pass)
    {
        String query="Select "+DB_SID+","+DB_SNAME+","+DB_USERNAME+","+DB_PASSWORD+","+DB_BRANCH+","+DB_SEMESTER+","+DB_SECTION+" from "+DB_STDTABLE+" where "+DB_USERNAME+"='"+email+"' and "+DB_PASSWORD+"='"+pass+"'";
        return db.rawQuery(query,null);
    }
    public Cursor retrieveattendance(String sid)
    {
        String query="Select count("+DB_ATTENDANCE+") from "+DB_ATTETABLE+" where "+DB_SID+"='"+sid+"' and "+DB_ATTENDANCE+"= 'P'";
        return db.rawQuery(query,null);
    }

    public Cursor retrievetotal(String sid)
    {
        String query="Select count("+DB_ATTENDANCE+") from "+DB_ATTETABLE+" where "+DB_SID+"='"+sid+"'";
        return db.rawQuery(query,null);
    }

    public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper()
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            db.execSQL(DB_ACREATE);
        }

        @Override

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_STDTABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DB_ATTETABLE);
            Log.d("upgrade", "DATABASE TABLE upgraded from version " + oldVersion + " to " + newVersion);
            onCreate(db);
        }
    }
}
