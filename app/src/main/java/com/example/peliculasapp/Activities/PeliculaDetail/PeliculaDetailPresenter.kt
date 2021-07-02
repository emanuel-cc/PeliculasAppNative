package com.example.peliculasapp.Activities.PeliculaDetail

import com.example.peliculasapp.ObjetoPelicula.PeliculaDetail
import com.example.peliculasapp.PeliculaDetailInterface

class PeliculaDetailPresenter(view:PeliculaDetailInterface.View):PeliculaDetailInterface.Presenter {
    var view:PeliculaDetailInterface.View = view
    lateinit var model:PeliculaDetailInterface.Model

    override fun getPeliculaDetail(movie_id: Long, api_key: String, language:String) {
        model = PeliculaDetailModel(this)
        model.getPeliculaDetail(movie_id, api_key, language)
    }

    override fun getDetailPelicula(pelicula: PeliculaDetail) {
        view.getDetailPelicula(pelicula)
    }

}