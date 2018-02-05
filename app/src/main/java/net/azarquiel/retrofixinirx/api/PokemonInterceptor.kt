package net.azarquiel.retrofixinirx.api

import android.util.Log
import okhttp3.*

/**
 * Created by fran on 2/2/18.
 */


class PokemonInterceptor(val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        Log.i("PokemonInterceptor", "prueba")
        val url = request.url().newBuilder()
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("format", "json")
                .build()

        val newRequest = request.newBuilder()
                .url(url)
                .addHeader("Cache-Control", "public, max-age=100")
                .build()

        val response: Response = chain . proceed (newRequest)
        Log.i("PokemonInterceptor", response.toString())
        return response
    }
}