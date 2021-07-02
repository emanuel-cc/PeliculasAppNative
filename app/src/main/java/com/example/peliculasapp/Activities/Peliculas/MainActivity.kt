package com.example.peliculasapp.Activities.Peliculas


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peliculasapp.Activities.PeliculaDetail.PeliculaDetailActivity
import com.example.peliculasapp.Adapter.AdapterEstreno
import com.example.peliculasapp.Adapter.AdapterPopular
import com.example.peliculasapp.Adapter.AdapterTopRated
import com.example.peliculasapp.ObjetoPelicula.Result
import com.example.peliculasapp.OnClickListener
import com.example.peliculasapp.PeliculasInterface
import com.example.peliculasapp.R
import com.example.peliculasapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PeliculasInterface.View, OnClickListener {
    lateinit var presenter: PeliculasInterface.Presenter
    lateinit var recycler:RecyclerView
    lateinit var recyclerPopular:RecyclerView
    lateinit var recyclerTopRated:RecyclerView
    lateinit var adapterEstreno: AdapterEstreno
    lateinit var adapterPopular:AdapterPopular
    lateinit var adapterTopRated:AdapterTopRated
    lateinit var mBinding:ActivityMainBinding
    //25d31599243ac9c6e9dd94435ea96737

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        presenter = PeliculaPresenter(this)
        recycler = findViewById(R.id.rec_estrenos)
        recyclerPopular = findViewById(R.id.rec_populares)
        recyclerTopRated = findViewById(R.id.rec_top_rank)
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerPopular.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerTopRated.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        presenter.getEstrenos("25d31599243ac9c6e9dd94435ea96737", "es-ES")
        presenter.getPopulares("25d31599243ac9c6e9dd94435ea96737", "es-ES")
        presenter.getTopRated("25d31599243ac9c6e9dd94435ea96737", "es-ES")
    }

    override fun showProgressEstreno() {
        mBinding.progEstreno.visibility = View.VISIBLE
        mBinding.recEstrenos.visibility = View.GONE
    }

    override fun hideProgressEstreno() {
        mBinding.progEstreno.visibility = View.GONE
        mBinding.recEstrenos.visibility = View.VISIBLE
    }

    override fun showProgressPopulares() {
        mBinding.progPopular.visibility = View.VISIBLE
        mBinding.recPopulares.visibility = View.GONE
    }

    override fun hideProgressPopulares() {
        mBinding.progPopular.visibility = View.GONE
        mBinding.recPopulares.visibility = View.VISIBLE
    }

    override fun showProgressTopRank() {
        mBinding.progTop.visibility = View.VISIBLE
        mBinding.recTopRank.visibility = View.GONE
    }

    override fun hideProgressTopRank() {
        mBinding.progTop.visibility = View.GONE
        mBinding.recTopRank.visibility = View.VISIBLE
    }

    override fun listaEstrenos(lista: List<Result>) {
        /*for (pelicula in lista){
            println(pelicula.title)
        }*/
        adapterEstreno = AdapterEstreno(lista, this)
        recycler.adapter = adapterEstreno
    }

    override fun listaPopulares(lista: List<Result>) {
        adapterPopular = AdapterPopular(lista, this)
        recyclerPopular.adapter = adapterPopular
    }

    override fun listaTopRated(lista: List<Result>) {
        adapterTopRated = AdapterTopRated(lista, this)
        recyclerTopRated.adapter = adapterTopRated
    }

    fun goPeliculaDetail(movieid: Long){
        var intent = Intent(this, PeliculaDetailActivity::class.java)
        intent.putExtra("movieid", movieid)
        startActivity(intent)
    }

    override fun onClick(movieId: Long) {
        goPeliculaDetail(movieId)
    }
}