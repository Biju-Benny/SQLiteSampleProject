package com.example.sqlitesampleproject

import android.annotation.SuppressLint
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

        //insert row, it will return record id of saved record
        val id = db.insert(Constants.TABLE_NAME,null,values)
        //close db connection
        db.close()
        //return id of inserted  record
        return id


    }
    //get all data
    @SuppressLint("Range")
    fun getAllData(orderBy:String): ArrayList<ModelRecord>{

        val recordList = ArrayList<ModelRecord>()
        val selectQuery = "SELECT * FROM ${Constants.TABLE_NAME} ORDER BY $orderBy"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)

        if (cursor.moveToFirst()){
            do{
                val modelRecord = ModelRecord(
                    ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_BIO)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_PHONE)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_EMAIL)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_DOB)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADDED_TIME_STAMP)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATED_TIME_STAMP))
                )
                //add record to list
                recordList.add(modelRecord)
            }while (cursor.moveToNext())
        }
        db.close()
        return  recordList

    }
    //search data
    @SuppressLint("Range")
    fun searchData(query: String):ArrayList<ModelRecord>{
        val recordList = ArrayList<ModelRecord>()
        val selectQuery = "SELECT * FROM ${Constants.TABLE_NAME} WHERE ${Constants.C_NAME} LIKE '% $query' "
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery,null)

        if (cursor.moveToFirst()){
            do{
                val modelRecord = ModelRecord(
                    ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_NAME)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_BIO)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_PHONE)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_EMAIL)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_DOB)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_ADDED_TIME_STAMP)),
                    ""+cursor.getString(cursor.getColumnIndex(Constants.C_UPDATED_TIME_STAMP))
                )
                //add record to list
                recordList.add(modelRecord)
            }while (cursor.moveToNext())
        }
        db.close()
        return  recordList

    }


}