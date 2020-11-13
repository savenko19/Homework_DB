package com.example.homework.model.manager

import android.content.Context
import com.example.homework.model.module.StorageModuleImpl

class HomeworkManagerFactory {

    companion object {
        @JvmStatic
        fun createManager(context: Context): HomeworkManager {
            val storageModule = StorageModuleImpl(context)
            return HomeworkManager(storageModule)
        }
    }
}