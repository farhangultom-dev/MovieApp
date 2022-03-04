package com.farhandev.movieapp.service.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tb_movie")
@Parcelize
data class MovieModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val backdropPath: String?,
    val title: String?,
    val rating: String?,
    val bookmarked: Boolean = false
): Parcelable