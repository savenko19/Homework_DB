package com.example.homework.model.module

import android.content.ContentValues
import android.content.Context
import com.example.homework.model.pojo.PersonNumber
import com.example.homework.model.storage.PersonNumberDbHelper

class StorageModuleImpl(context: Context) : StorageModule {


    private val dbHelper = PersonNumberDbHelper.getInstance(context)

    override fun addNumber(personNumber: PersonNumber) {
        val db = dbHelper.writableDatabase
        val values = ContentValues()

        values.put(PersonNumberDbHelper.COLUMN_NAME, personNumber.name)
        values.put(PersonNumberDbHelper.COLUMN_NUMBER, personNumber.number)

        db.insert(
            PersonNumberDbHelper.TABLE_NAME,
            null,
            values
        )

        db.close()
    }

    override val numbers: ArrayList<PersonNumber>
        get() {

            val personNumbers = ArrayList<PersonNumber>()
            val query = "SELECT * FROM ${PersonNumberDbHelper.TABLE_NAME}"

            val db = dbHelper.writableDatabase
            val cursor = db.rawQuery(query, null)
            if (cursor != null) {

                while (cursor.moveToNext()) {
                    val personNumber = PersonNumber(
                        cursor.getString(cursor.getColumnIndex(PersonNumberDbHelper.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(PersonNumberDbHelper.COLUMN_NUMBER))
                    )

                    personNumbers.add(personNumber)
                }
            }

            cursor.close()
            return personNumbers
        }
}