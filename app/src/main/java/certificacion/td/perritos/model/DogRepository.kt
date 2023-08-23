package certificacion.td.perritos.model

import android.util.Log
import androidx.lifecycle.LiveData
import certificacion.td.perritos.model.local.Dogdao
import certificacion.td.perritos.model.local.entities.ImagesEntity
import certificacion.td.perritos.model.remote.RetrofitClient

class DogRepository(private val dogdao: Dogdao) {

    private val networkService= RetrofitClient.retrofitInstance()
    val breedsListLiveData=dogdao.getAllBreedList()

    suspend fun fetchBreed(){
        val service=kotlin.runCatching {
            networkService.getBreedsList()
        }
        service.onSuccess {
            when (it.code()){
                in 200..299-> it.body()?.let {
                    dogdao.insertAllBreedList(fromInternettoBreedEntity(it))
                }
                else -> Log.d("REPO", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("ERROR", "${it.message}")
        }
    }

    suspend fun fetchImage(breed: String){
        val service= kotlin.runCatching {
            networkService.getImagesList(breed)
        }

        service.onSuccess {
            when(it.code()){
                200-> it.body()?.let {
                    dogdao.insertAllImagesList(fromInternetToImagesEntity(it,breed))
                }
                else->Log.d("REPO-IMAGE", "${it.code()} - ${it.errorBody()}")
            }
        }
        service.onFailure {
            Log.e("REPO", "${it.message}")
        }
    }

    fun getAllImagesByBreed(breed: String): LiveData<List<ImagesEntity>>{
        return dogdao.getAllDoggiesImages(breed)
    }

    suspend fun updateFavouritesImages(imagesEntity: ImagesEntity){
        dogdao.updateFavouriteImages(imagesEntity)
    }

    suspend fun deleteFavouritesImages(){
        dogdao.deleteFavImages()
    }


}