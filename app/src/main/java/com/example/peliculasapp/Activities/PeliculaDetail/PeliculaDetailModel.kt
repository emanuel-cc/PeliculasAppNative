package com.example.peliculasapp.Activities.PeliculaDetail

import android.util.Log
import com.example.peliculasapp.ObjetoPelicula.PeliculaDetail
import com.example.peliculasapp.PeliculaDetailInterface
import com.example.peliculasapp.PeliculasApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PeliculaDetailModel(presenter:PeliculaDetailInterface.Presenter):PeliculaDetailInterface.Model {
    lateinit var retrofit: Retrofit
    var presenter:PeliculaDetailInterface.Presenter = presenter

    override fun getPeliculaDetail(movie_id: Long, api_key: String, language:String) {
        // Se crea la instancia de retrofit
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Se crea la instancia del servicio api con la referencia retrofit
        var service = retrofit.create(PeliculasApi::class.java)

        // Se hace el llamado del servicio
        var call = service.getPeliculaDetail(movie_id, api_key, language)

        // Se hace la petición asyncrona
        call.enqueue(object:Callback<PeliculaDetail>{
            override fun onResponse(
                call: Call<PeliculaDetail>,
                response: Response<PeliculaDetail>
            ) {
                if(response.isSuccessful){
                    Log.i("PeliculaDetail", "${response.body()!!.title}")
                    presenter.getDetailPelicula(response.body()!!)
                }else{
                    Log.i("Error","${response.message()}")
                }
            }

            override fun onFailure(call: Call<PeliculaDetail>, t: Throwable) {
                Log.i("Response Failed", "${t.message}")
            }

        })
    }

}