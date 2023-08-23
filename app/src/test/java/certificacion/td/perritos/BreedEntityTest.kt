package certificacion.td.perritos

import certificacion.td.perritos.model.local.entities.BreedEntity
import org.junit.Before
import org.junit.Test

class BreedEntityTest {

    private lateinit var breedEntity: BreedEntity

    @Before
    fun setup(){
        breedEntity= BreedEntity(breed = "Chilean Quiltro")
    }

    @Test
    fun testBreed(){
        assert(breedEntity.breed=="Chilean Quiltro")
    }
}