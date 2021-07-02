package com.example.peliculasapp

import com.example.peliculasapp.ObjetoPelicula.PeliculaDetail
import com.example.peliculasapp.ObjetoPelicula.PeliculaResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeliculasApi {
    @GET("movie/now_playing")
    fun getListEstrenos(@Query("api_key") api_key:String, @Query("language") language:String):Call<PeliculaResponse>

    @GET("movie/popular")
    fun getListPopulares(@Query("api_key") api_key:String, @Query("language") language:String):Call<PeliculaResponse>

    @GET("movie/top_rated")
    fun getListTopRated(@Query("api_key") api_key: String, @Query("language") language: String):Call<PeliculaResponse>

    @GET("movie/{movie_id}")
    fun getPeliculaDetail(@Path("movie_id") movie_id:Long, @Query("api_key") api_key: String, @Query("language") language: String):Call<PeliculaDetail>
}