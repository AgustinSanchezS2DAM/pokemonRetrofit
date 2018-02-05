package net.azarquiel.retrofixinirx.api

import net.azarquiel.retrofixinirx.model.Tema
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import rx.android.BuildConfig


/**
 * Created by pacopulido on 28/1/18.
 */



interface PokemonApiServiceDrive {

    @FormUrlEncoded
    @POST("tema")
//    @Headers("Content-Type: application/json")
//    fun savePokemon(
//            @Body Tema: Tema):Observable<String>

    fun savePokemon(
            @Field("descripcion") name: String):Observable<Tema>

    // común a todas las instancias de esa clase pues será un singleton
    companion object {
        fun create(): PokemonApiServiceDrive {

            val client = OkHttpClient().newBuilder()
                    .addInterceptor(PokemonInterceptor("http://www.ies-azarquiel.es/paco/foroslim/"))
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    })
                    .build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://www.ies-azarquiel.es/paco/foroslim/")
                    .client(client)
                    .build()

            return retrofit.create(PokemonApiServiceDrive::class.java)
        }
    }

}