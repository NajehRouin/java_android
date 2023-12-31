package com.example.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context,"Contact.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase Db) {
        Db.execSQL("create Table user(nom TEXT primary key , prenom TEXT ,email TEXT ,numero_tel TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Db, int i, int i1) {
Db.execSQL("drop Table if exists user");
    }
    public Boolean insertData(String nom,String prenom,String email,String numero_tel){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("nom",nom);
        cv.put("prenom",prenom);
        cv.put("email",email);
        cv.put("numero_tel",numero_tel);
        long result =DB.insert("user",null,cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean UpdateData(String nom,String prenom,String email,String numero_tel){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("prenom",prenom);
        cv.put("email",email);
        cv.put("numero_tel",numero_tel);

        Cursor cr=DB.rawQuery("Select * from user where nom = ?",new String[]{nom});
      if(cr.getCount()>0){
          long result =DB.update("user",cv,"nom=?",new String[]{nom});
          if(result==-1){
              return false;
          }else{
              return true;
          }
      }else {
          return false;
      }



    }

    public Boolean DeleteData(String nom){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cr=DB.rawQuery("Select * from user where nom = ?",new String[]{nom});
        if(cr.getCount()>0){
            long result =DB.delete("user","nom=?",new String[]{nom});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else {
            return false;
        }



    }

    public Cursor getData(){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cr=DB.rawQuery("Select * from user",null);

return cr;


    }
}
