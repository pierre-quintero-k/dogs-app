package certificacion.td.perritos.model.local.entities

import androidx.room.Entity


@Entity(tableName = "images_table", primaryKeys = ["breed","imageUrl"])
data class ImagesEntity (
    val imageUrl: String,
    val breed: String,
    var favourite: Boolean=false

        )