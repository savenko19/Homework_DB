package com.example.homework

import android.app.Application
import android.content.Context
import com.example.homework.model.manager.HomeworkManagerFactory
import com.example.homework.model.manager.Manager

class App : Application() {

    private var mManager: Manager? = null

    override fun onCreate() {
        super.onCreate()
        mManager = HomeworkManagerFactory.createManager(this)
    }

    companion object {
        private fun getApp(context: Context):App {
            return context.applicationContext as App
        }

        fun getManager(context:Context):Manager {
            return getApp(context).mManager!!
        }
    }


}