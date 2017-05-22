package com.example.data.dao

import com.example.data.local.AppDataBase
import com.example.domain.models.Photo
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

/**
 * Data access object to save the photo object
 */
@Table(database = AppDataBase::class)
class PhotoDAO(): BaseModel() {

    @PrimaryKey
    lateinit var id: String
    @Column
    lateinit var url: String

    constructor(photo: Photo): this() {
        id = photo.id
        url = photo.url
    }

    fun toPhoto() = Photo(id, url)

}