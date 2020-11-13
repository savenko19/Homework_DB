package com.example.homework.model.storage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class PersonNumberDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "PERSON_NUMBER"
        const val TABLE_NAME = "NUMBER"
        const val COLUMN_NAME = "name"
        const val COLUMN_NUMBER = "number"

        fun getInstance(context: Context) = PersonNumberDbHelper(context)
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NAME ($COLUMN_NAME TEXT PRIMARY KEY, $COLUMN_NUMBER TEXT)")
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }
}