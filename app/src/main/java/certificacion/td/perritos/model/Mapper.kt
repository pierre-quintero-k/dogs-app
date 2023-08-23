package certificacion.td.perritos.model

import certificacion.td.perritos.model.local.entities.BreedEntity
import certificacion.td.perritos.model.local.entities.ImagesEntity
import certificacion.td.perritos.model.remote.fromInternet.Breed
import certificacion.td.perritos.model.remote.fromInternet.Images


fun fromInternettoBreedEntity(breed: Breed): List<BreedEntity>{

    val breedNames= breed.message.keys
    return breedNames.map {
        breedNames->
        BreedEntity(breed= breedNames)
    }
}

fun fromInternetToImagesEntity(images: Images, breed: String): List<ImagesEntity>{

    val imageName= images.message
    return imageName.map {
        imageName->
        ImagesEntity(
            imageUrl = imageName,
            breed= breed
        )
    }
}