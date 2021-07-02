package com.example.peliculasapp.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculasapp.ObjetoPelicula.Result
import com.example.peliculasapp.OnClickListener
import com.example.peliculasapp.R

class AdapterTopRated(listaTopRated:List<Result>, private var listener:OnClickListener):RecyclerView.Adapter<AdapterTopRated.ViewHolderDatos>() {
    var listaTopRated:List<Result> = listaTopRated

    // Sirve para enlazar el adaptador con el archivo item_top_rated creado
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterTopRated.ViewHolderDatos {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_top_rated, null, false)
        return ViewHolderDatos(view)
    }

    // Establece la comunicación entre el adaptador y la clase viewHolderDatos
    override fun onBindViewHolder(holder: AdapterTopRated.ViewHolderDatos, position: Int) {
        holder.asignarDatos(listaTopRated.get(position))
    }

    // Regresa el tamaño de la lista
    override fun getItemCount(): Int {
        return listaTopRated.size
    }

    // Se hace referencia al item del layout
   inner class ViewHolderDatos(itemView:View):RecyclerView.ViewHolder(itemView) {
        var imgTop:ImageView = itemView.findViewById(R.id.img_top)
        var txtTitleTop:TextView = itemView.findViewById(R.id.txt_title_top)

        fun asignarDatos(itemTopRated:Result){
            var baseUrlimg = "https://image.tmdb.org/t/p/original"
            var uri: Uri = Uri.parse("${baseUrlimg}${itemTopRated.poster_path}")
            Glide.with(itemView.context).load(uri).into(imgTop)
            txtTitleTop.setText(itemTopRated.title)
            /*itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(itemView.context, "${itemTopRated.title}", Toast.LENGTH_SHORT).show()
            })*/
            itemView.setOnClickListener {
                listener.onClick(itemTopRated.id)
            }
        }
    }
}