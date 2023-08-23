package certificacion.td.perritos.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import certificacion.td.perritos.model.local.entities.BreedEntity
import certificacion.td.perritos.model.local.entities.ImagesEntity


@Database(entities = [BreedEntity::class, ImagesEntity::class], version = 1, exportSchema = false)
abstract class DogDatabase: RoomDatabase() {
    abstract fun getDogDao(): Dogdao

    companion object{

        @Volatile
        private var INSTANCE: DogDatabase?=null

        fun getDatabase(context: Context): DogDatabase {
            val tempInstance= INSTANCE
            if (tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    DogDatabase::class.java,
                    "dog_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}