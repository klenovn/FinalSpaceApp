package com.klenovn.finalspaceapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class FinalSpaceDatabase : RoomDatabase() {
    abstract val dao: FinalSpaceDao
}
