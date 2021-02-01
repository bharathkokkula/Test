package test.app.retrofit

import test.app.doa.ImageData
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ApiHelper {
    @GET("/api/breeds/image/random")
    fun getImage() : Observable<Response<ImageData>>

}