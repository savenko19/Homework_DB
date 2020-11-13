package com.example.homework.model.manager

import com.example.homework.model.module.StorageModule
import com.example.homework.model.pojo.PersonNumber

class HomeworkManager(storageModule: StorageModule, ) : Manager {

    private val storageModule = storageModule

    override fun addNumber(personNumber: PersonNumber) {
        storageModule.addNumber(personNumber)
    }

    override val numbers: ArrayList<PersonNumber>
        get() = storageModule.numbers
}