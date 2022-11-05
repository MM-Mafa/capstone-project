package com.example.fingerprintvotingsystem.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context,"capstone_project",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE PARTY(" +
                "PARTY_ID INT (2) PRIMARY KEY," +
                "PARTY_NAME VARCHAR(100))");

        db.execSQL("CREATE TABLE CITIZEN(" +
                "CITIZEN_ID VARCHAR(13) PRIMARY KEY," +
                "CITIZEN_NAME VARCHAR(100)," +
                "CITIZEN_SURNAME VARCHAR(100)," +
                "CITIZEN_EMAIL VARCHAR(100) UNIQUE," +
                "CITIZEN_PASSWORD VARCHAR(100)," +
                "PARTY_ID INT (2)," +
                "FOREIGN KEY (PARTY_ID) REFERENCES PARTY(PARTY_ID))");


        db.execSQL("" +
                "CREATE TABLE ELECTION(" +
                "ELECTION_ID VARCHAR(13) PRIMARY KEY," +
                "ELECTION_TIME  TEXT, " +
                "ELECTION_DATE TEXT," +
                "PARTY_ID INT (2)," +
                "CITIZEN_ID VARCHAR(13)," +
                "FOREIGN KEY (PARTY_ID) REFERENCES PARTY(PARTY_ID)," +
                "FOREIGN KEY (CITIZEN_ID) REFERENCES CITIZEN(CITIZEN_ID))");




    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS ELECTION");
        db.execSQL("DROP TABLE IF EXISTS CITIZEN");
        db.execSQL("DROP TABLE IF EXISTS PARTY");
    }

    public  boolean insertDataToCitizen(String name, String surname, String id, String email, String password, int partyId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("CITIZEN_ID",id);
        contentValues.put("CITIZEN_NAME",name);
        contentValues.put("CITIZEN_SURNAME",surname);
        contentValues.put("CITIZEN_EMAIL",email);
        contentValues.put("CITIZEN_PASSWORD",password);
        contentValues.put("PARTY_ID",partyId);
       long results = db.insert("CITIZEN",null,contentValues);
       return results != -1;
    }

    public  boolean insertDataIntoParty(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("PARTY_ID",id);
        contentValues.put("PARTY_NAME",name);
        long results = db.insert("PARTY",null,contentValues);
        return results != -1;
    }

    public  boolean insertDataIntoElection(String electionId, String time, String date, String citizenId, int partyId ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("ELECTION_ID",electionId);
        contentValues.put("PARTY_ID",partyId);
        contentValues.put("CITIZEN_ID",citizenId);
        contentValues.put("ELECTION_TIME",time);
        contentValues.put("ELECTION_DATE",date);
        long results = db.insert("ELECTION",null,contentValues);
        return results != -1;
    }


    public  Cursor getDataFromCitizenByEmail(String EMAIL, boolean selectAll){

        if(selectAll){
            SQLiteDatabase db = this.getWritableDatabase();
            return db.rawQuery("SELECT * FROM CITIZEN WHERE CITIZEN_EMAIL LIKE '"+EMAIL+"'",null);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT CITIZEN_PASSWORD FROM CITIZEN WHERE CITIZEN_EMAIL LIKE '"+EMAIL+"'",null);
    };
    public  Cursor getDataFromCitizenById(String ID){

        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM CITIZEN WHERE CITIZEN_ID LIKE '"+ID+"'",null);

    };

    public  Cursor getDataFromCitizen(String Table, String userInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.rawQuery("SELECT * FROM '"+Table+"' CITIZEN WHERE CITIZEN_EMAIL LIKE  ' "+userInfo +"'",null);
    };

    public  boolean  populatePartyTable(){
        int count = 0 ;
       if(insertDataIntoParty(11,"ANC"))
           count+=1;
        if(insertDataIntoParty(12,"DA"))
            count+=1;
        if(insertDataIntoParty(13,"EFF"))
            count+=1;
        if(insertDataIntoParty(14,"UDM"))
            count+=1;
        if(insertDataIntoParty(15,"COPE"))
            count+=1;

        if(insertDataIntoParty(16,"ALJAMA"))
            count+=1;
        if(insertDataIntoParty(17,"VF+"))
            count+=1;
        if(insertDataIntoParty(18,"ACDP"))
            count+=1;
        if(insertDataIntoParty(19,"AIC"))
           count+=1;
        if(insertDataIntoParty(20,"PAC"))
            count+=1;

        if(insertDataIntoParty(21,"GOOD"))
            count+=1;
        if(insertDataIntoParty(22,"IFP"))
            count+=1;
        if(insertDataIntoParty(23,"ATM"))
            count+=1;

        return count == 13;
    }

    public  int getListOfRows(String table){
        int count = 0 ;
        SQLiteDatabase db = this.getWritableDatabase();
         Cursor data = db.rawQuery("SELECT * FROM '"+table+"'",null);

         if(data!=null)
            count= data.getCount();


        return count;
    }
}
