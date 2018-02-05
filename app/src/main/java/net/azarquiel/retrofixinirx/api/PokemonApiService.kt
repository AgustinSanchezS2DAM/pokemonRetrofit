package net.azarquiel.retrofixinirx.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import net.azarquiel.retrofixinirx.model.Pokemon
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by pacopulido on 28/1/18.
 */

// GET = https://pokeapi.co/api/v2/pokemon/?limit=802


interface PokemonApiService {

    // Utilizamos Observables en lugar de Call porque utilizamos RX
    @GET("pokemon") // ?limit=tal
    fun getData(@Query("limit") limit: String): Observable<Pokemon>



    // común a todas las instancias de esa clase pues será un singleton
    companion object {
        fun create(): PokemonApiService {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://pokeapi.co/api/v2/")
                    .build()

            return retrofit.create(PokemonApiService::class.java)
        }
    }

}