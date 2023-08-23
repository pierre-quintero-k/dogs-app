package certificacion.td.perritos

import certificacion.td.perritos.model.remote.RetrofitClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setup(){
        mockWebServer= MockWebServer()
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun testRetrofit(){
        val expectedBaseUrl= mockWebServer.url("agua/").toString()

        val retrofit= Retrofit.Builder().baseUrl(expectedBaseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()


        //usamos retrofit para pasar nuestra conexion simulada
        RetrofitClient.retrofitInstance=retrofit
        val retrofitInstance= RetrofitClient.retrofitInstance
        assertEquals(expectedBaseUrl,retrofitInstance.baseUrl().toString())
    }
}