package com.example.peliculasapp.Activities.PeliculaDetail

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculasapp.Adapter.AdapterProduction
import com.example.peliculasapp.ObjetoPelicula.PeliculaDetail
import com.example.peliculasapp.OnClickListener
import com.example.peliculasapp.PeliculaDetailInterface
import com.example.peliculasapp.R
import com.example.peliculasapp.databinding.ActivityPeliculaDetailBinding

class PeliculaDetailActivity : AppCompatActivity(), PeliculaDetailInterface.View {
    lateinit var presenter: PeliculaDetailPresenter
    lateinit var mBinding:ActivityPeliculaDetailBinding
    lateinit var recycler:RecyclerView
    lateinit var adapter:AdapterProduction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPeliculaDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        presenter = PeliculaDetailPresenter(this)
        var movieid = intent.getLongExtra("movieid", 0)
        presenter.getPeliculaDetail(movieid ,"25d31599243ac9c6e9dd94435ea96737", "es-ES")
        recycler = mBinding.recProd
        recycler.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL, false)
    }

    override fun getDetailPelicula(pelicula: PeliculaDetail) {
        var baseUrlimg = "https://image.tmdb.org/t/p/original"
        var uri: Uri = Uri.parse("${baseUrlimg}${pelicula.poster_path}")

        Glide.with(this).load(uri).into(mBinding.imgPhoto)
        mBinding.txtTitle.setText(pelicula.title)
        mBinding.descripcionMovie.setText(pelicula.overview)
        mBinding.txtpopularity.setText("Popularidad: ${pelicula.popularity}")
        mBinding.txtrelease.setText("Lanzamiento: ${pelicula.release_date}")
        mBinding.txtAverage.setText(pelicula.vote_average.toString())
        mBinding.txtgenreitem.setText("- ${pelicula.genres.get(0).name}")

        adapter = AdapterProduction(pelicula.production_companies)
        recycler.adapter = adapter
    }
}