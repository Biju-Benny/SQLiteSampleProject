package com.example.sqlitesampleproject

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context?): SQLiteOpenHelper(context,Constants.DB_NAME,
    null,Constants.DB_VERSION)

{
    override fun onCreate(db: SQLiteDatabase) {
        //create table on that db
        db.execSQL(Constants.CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //upgrade database(if there ia any structural changes, change DB version)
        //drop older table if exists

        db.execSQL("DROP TABLE IF EXISTS"+Constants.TABLE_NAME)
        onCreate(db)

    }

    //insert record to db
    fun insertRecord(
        name:String?,
        image:String?,
        bio:String?,
        phone:String?,
        email:String?,
        dob:String?,
        addedTime:String?,
        updatedTime:String?
    ):Long{
        //get writable data base because we want to write data
        val db = this.writableDatabase
        val values = ContentValues()
        //id will be put automatically as auto increment is implemented
        //insert data
        values.put(Constants.C_NAME,name)
        values.put(Constants.C_IMAGE,image)
        values.put(Constants.C_BIO,bio)
        values.put(Constants.C_PHONE,phone)
        values.put(Constants.C_EMAIL,email)
        values.put(Constants.C_DOB,dob)
        values.put(Constants.C_ADDED_TIME_STAMP,addedTime)
        values.put(Constants.C_UPDATED_TIME_STAMP,updatedTime)

        //insert row, it will return record id ofsaved record
        val id = db.insert(Constants.TABLE_NAME,null,values)
        //close db connection
        db.close()
        //return id of inserted  record
        return id


    }


}