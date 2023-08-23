package certificacion.td.perritos

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import certificacion.td.perritos.view.MainActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntentTest {

    @Test
    fun testIntentToMainActivity() {


        val context = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation()
            .targetContext


        val intent = Intent(context, MainActivity::class.java)



        intent.putExtra("id", 1)
        intent.putExtra("name", "phone prueba")
        intent.putExtra("price", 1)
        intent.putExtra("image", "test image")
        intent.putExtra("description", "test description")
        intent.putExtra("lastPrice", 1)
        intent.putExtra("credit", true)



        val activityScenario = ActivityScenario.launch<MainActivity>(intent)



        activityScenario.onActivity { activity ->


            assertNotNull(activity)



            assertEquals(1, activity.intent.getIntExtra("id", -1))
            assertEquals("phone prueba", activity.intent.getStringExtra("name",))
            assertEquals(1, activity.intent.getIntExtra("price", -1))
            assertEquals("test image", activity.intent.getStringExtra("image",))
            assertEquals("test description", activity.intent.getStringExtra("description",))
            assertEquals(1, activity.intent.getIntExtra("lastPrice", -1))
            assertEquals(true, activity.intent.getBooleanExtra("credit", false))


        }
    }
}
