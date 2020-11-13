package com.example.homework.model.manager

import com.example.homework.model.pojo.PersonNumber

interface Manager {

    fun addNumber(personNumber: PersonNumber)

    val numbers: ArrayList<PersonNumber>
}