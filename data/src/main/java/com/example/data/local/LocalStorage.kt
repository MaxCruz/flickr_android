package com.example.data.local

import com.raizlabs.android.dbflow.config.DatabaseDefinition
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.Model

class LocalStorage {

    private val mDataBase: DatabaseDefinition = FlowManager.getDatabase(AppDataBase::class.java)

    fun <T: Model> saveObjectList(clazz: Class<T>, list: List<T>) {
        val adapter = mDataBase.getModelAdapterForTable(clazz)
        adapter?.insertAll(list)
    }

    fun <T: Model> deleteAllObjects(clazz: Class<T>) {
        val adapter = mDataBase.getModelAdapterForTable(clazz)
        adapter?.deleteAll(retrieveAllObjects(clazz))
    }

    fun <T: Model> retrieveAllObjects(clazz: Class<T>): List<T> {
        return SQLite.select().from(clazz).queryList()
    }

}