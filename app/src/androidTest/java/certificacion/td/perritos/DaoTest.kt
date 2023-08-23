package certificacion.td.perritos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import certificacion.td.perritos.model.local.DogDatabase
import certificacion.td.perritos.model.local.Dogdao
import certificacion.td.perritos.model.local.entities.BreedEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var dogDao: Dogdao
    private lateinit var db: DogDatabase

    @Before
    fun setup(){
        val context= ApplicationProvider.getApplicationContext<android.content.Context>()
        db= Room.inMemoryDatabaseBuilder(context,DogDatabase::class.java).build()
        dogDao=db.getDogDao()
    }

    @After
    fun shutDown(){
        db.close()
    }

    @Test
    fun insertBreedTest()= runBlocking{
        val dogBreeds= listOf(
            BreedEntity("raza1"),
            BreedEntity("raza2"),
            BreedEntity("raza3"),
            BreedEntity("raza4")
        )

        dogDao.insertAllBreedList(dogBreeds)

        val breedLiveData=dogDao.getAllBreedList()
        val breedList= breedLiveData.value?: emptyList()

        MatcherAssert.assertThat(breedList, CoreMatchers.not(emptyList()))
        MatcherAssert.assertThat(breedList.size, CoreMatchers.equalTo(4))
    }


}