package com.example.peliculasapp.Activities.Peliculas

import com.example.peliculasapp.ObjetoPelicula.PeliculaResponse
import com.example.peliculasapp.ObjetoPelicula.Result
import com.example.peliculasapp.PeliculasApi
import com.example.peliculasapp.PeliculasInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PeliculaModel(presenter: PeliculaPresenter) : PeliculasInterface.Model {
    lateinit var retrofit: Retrofit
    var presenter: PeliculasInterface.Presenter = presenter

    override fun getEstrenos(api_key: String, language: String) {
        // Se crea la instancia de retrofit
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Se crea la instancia del servicio api con la referencia retrofit
        var service = retrofit.create(PeliculasApi::class.java)

        // Se hace el llamado del servicio
        var call = service.getListEstrenos(api_key, language)

        // Se hace la petición asyncrona
        call.enqueue(object:Callback<PeliculaResponse>{
            override fun onResponse(call: Call<PeliculaResponse>, response: Response<PeliculaResponse>) {
                if(response.isSuccessful){
                    //println("Respuesta: ${response.body()?.results?.get(0)?.title}")
                    var res = response.body()
                    presenter.hideProgressEstreno()
                    presenter.listarEstrenos(res?.results as List<Result>)

                }else{
                    presenter.showProgressEstreno()
                }
            }

            override fun onFailure(call: Call<PeliculaResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }

        } )
    }

    override fun getPopulares(api_key: String, language: String) {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(PeliculasApi::class.java)

        var call = service.getListPopulares(api_key, language)
        call.enqueue(object:Callback<PeliculaResponse>{
            override fun onResponse(
                call: Call<PeliculaResponse>,
                response: Response<PeliculaResponse>
            ) {
                if(response.isSuccessful){
                    var res = response.body();
                    //println("Populares: ${response.body()?.results?.get(0)?.title}")
                    presenter.hideProgressPopulares()
                    presenter.listarPopulares(res?.results as List<Result>)
                }else{
                    presenter.showProgressPopulares()
                }
            }

            override fun onFailure(call: Call<PeliculaResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

    override fun getTopRated(api_key: String, language: String) {
        // Se crea una nueva instancia del servicio de retrofit
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Se crea una instancia del servicio de api con referencia de retrofit
        var service = retrofit.create(PeliculasApi::class.java)
        // Se hace la petición a ese servicio
        var call = service.getListTopRated(api_key, language)
        // Se hace la petición asyncrona
        call.enqueue(object:Callback<PeliculaResponse>{
            override fun onResponse(
                call: Call<PeliculaResponse>,
                response: Response<PeliculaResponse>
            ) {
                if(response.isSuccessful){
                    var res = response.body()
                    presenter.hideProgressTopRank()
                    //println("Top Rated: ${response.body()?.results?.get(0)?.title}")
                    presenter.listarTopRated(res?.results as List<Result>)
                }else{
                    presenter.showProgressTopRank()
                }
            }

            override fun onFailure(call: Call<PeliculaResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

}