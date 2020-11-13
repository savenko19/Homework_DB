package com.example.homework.model.module

import com.example.homework.model.pojo.PersonNumber

interface StorageModule {

    fun addNumber(personNumber: PersonNumber)

    val numbers: ArrayList<PersonNumber>
}