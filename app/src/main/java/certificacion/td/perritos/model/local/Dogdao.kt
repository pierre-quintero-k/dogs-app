package certificacion.td.perritos.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import certificacion.td.perritos.model.local.entities.BreedEntity
import certificacion.td.perritos.model.local.entities.ImagesEntity


@Dao
interface Dogdao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBreedList(listBreed: List<BreedEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllImagesList(dogsImages: List<ImagesEntity>)

    @Query("SELECT * FROM breed_table ORDER BY breed ASC")
    fun getAllBreedList(): LiveData<List<BreedEntity>>

    @Update
    suspend fun updateFavouriteImages(imagesEntity: ImagesEntity)

    @Query("UPDATE images_table SET favourite = 0 WHERE favourite = 1")
    suspend fun deleteFavImages()

    @Query("SELECT * FROM images_table WHERE breed = :breed")
    fun getAllDoggiesImages(breed : String): LiveData<List<ImagesEntity>>

    // Funcion que trae todos los favoritos
    @Query("SELECT * FROM images_table WHERE favourite = 1")
    fun getAllFavImages(): LiveData<List<ImagesEntity>>


}