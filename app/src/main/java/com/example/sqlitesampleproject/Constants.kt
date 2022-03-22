package com.example.sqlitesampleproject

object Constants {
    //DB NAME
    const val DB_NAME ="MY_RECORDS_DB"
    //db version
    const val DB_VERSION = 1
    //table name
    const val TABLE_NAME = "MY_RECORDS_TABLE"
    //columns
    const val C_ID = "ID"
    const val C_NAME = "NAME"
    const val C_IMAGE = "IMAGE"
    const val C_EMAIL = "EMAIL"
    const val C_PHONE = "PHONE"
    const val C_DOB = "DOB"
    const val C_BIO = "BIO"
    const val C_ADDED_TIME_STAMP = "ADDED_TIME_STAMP"
    const val C_UPDATED_TIME_STAMP = "UPDATED_TIME_STAMP"

    //create table query
    const val CREATE_TABLE =(
            "CREATE TABLE "+ TABLE_NAME+ "("+ C_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + C_NAME+ "TEXT,"
            + C_IMAGE+ "TEXT,"
            + C_EMAIL+ "TEXT,"
            + C_PHONE+ "TEXT,"
            + C_DOB+ "TEXT,"
            + C_BIO+ "TEXT,"
            + C_ADDED_TIME_STAMP+ "TEXT,"
            + C_UPDATED_TIME_STAMP+ "TEXT,"
            + ")"


            )

}