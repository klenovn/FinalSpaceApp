package com.klenovn.finalspaceapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CharacterEntity::class],
    version = 1
)
abstract class FinalSpaceDatabase : RoomDatabase() {
    abstract val dao: FinalSpaceDao
}