package certificacion.td.perritos.model.remote

import certificacion.td.perritos.model.remote.fromInternet.Breed
import certificacion.td.perritos.model.remote.fromInternet.Images
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi {

    @GET("breeds/list/all")
    suspend fun getBreedsList(): Response<Breed>

    @GET("breed/{breed}/images")
    suspend fun getImagesList(@Path("breed") breed: String): Response<Images>

}