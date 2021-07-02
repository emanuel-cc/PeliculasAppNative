package com.example.peliculasapp

import com.example.peliculasapp.ObjetoPelicula.PeliculaDetail
import com.example.peliculasapp.ObjetoPelicula.Result


interface PeliculasInterface {
    interface Model{
        fun getEstrenos(api_key:String, language:String)
        fun getPopulares(api_key: String, language: String)
        fun getTopRated(api_key: String, language: String)
    }

    interface Presenter{
        fun getEstrenos(api_key:String, language:String)
        fun getPopulares(api_key: String, language: String)
        fun getTopRated(api_key: String, language: String)
        fun listarEstrenos(lista: List<Result>)
        fun listarPopulares(lista: List<Result>)
        fun listarTopRated(lista: List<Result>)
        fun showProgressEstreno()
        fun hideProgressEstreno()
        fun showProgressPopulares()
        fun hideProgressPopulares()
        fun showProgressTopRank()
        fun hideProgressTopRank()
    }

    interface View{
        fun showProgressEstreno()
        fun hideProgressEstreno()
        fun showProgressPopulares()
        fun hideProgressPopulares()
        fun showProgressTopRank()
        fun hideProgressTopRank()
        fun listaEstrenos(lista:List<Result>)
        fun listaPopulares(lista:List<Result>)
        fun listaTopRated(lista: List<Result>)
    }
}

interface PeliculaDetailInterface{
    interface Model{
        fun getPeliculaDetail(movie_id: Long, api_key: String, language: String)
    }
    interface Presenter{
        fun getPeliculaDetail(movie_id: Long, api_key: String, language: String)
        fun getDetailPelicula(pelicula:PeliculaDetail)
    }
    interface View{
        fun getDetailPelicula(pelicula:PeliculaDetail)
    }
}