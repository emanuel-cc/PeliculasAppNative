package com.example.peliculasapp.Activities.Peliculas

import com.example.peliculasapp.ObjetoPelicula.Result
import com.example.peliculasapp.PeliculasInterface

class PeliculaPresenter(view: PeliculasInterface.View) : PeliculasInterface.Presenter {
    var view: PeliculasInterface.View = view;
    lateinit var model: PeliculasInterface.Model;

    override fun getEstrenos(api_key: String, language: String) {
        model = PeliculaModel(this)

        model.getEstrenos(api_key, language)
    }

    override fun getPopulares(api_key: String, language: String) {
        model = PeliculaModel(this)
        model.getPopulares(api_key, language)
    }

    override fun getTopRated(api_key: String, language: String) {
        model = PeliculaModel(this)
        model.getTopRated(api_key, language)
    }

    override fun listarEstrenos(lista: List<Result>) {
        view.listaEstrenos(lista)
    }

    override fun listarPopulares(lista: List<Result>) {
        view.listaPopulares(lista)
    }

    override fun listarTopRated(lista: List<Result>) {
        view.listaTopRated(lista)
    }

    override fun showProgressEstreno() {
        view.showProgressEstreno()
    }

    override fun hideProgressEstreno() {
        view.hideProgressEstreno()
    }

    override fun showProgressPopulares() {
        view.showProgressPopulares()
    }

    override fun hideProgressPopulares() {
        view.hideProgressPopulares()
    }

    override fun showProgressTopRank() {
        view.showProgressTopRank()
    }

    override fun hideProgressTopRank() {
        view.hideProgressTopRank()
    }
}