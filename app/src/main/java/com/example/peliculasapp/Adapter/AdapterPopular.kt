package com.example.peliculasapp.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculasapp.ObjetoPelicula.Result
import com.example.peliculasapp.OnClickListener
import com.example.peliculasapp.R

class AdapterPopular (listaPopulares:List<Result>, private var listener:OnClickListener) : RecyclerView.Adapter<AdapterPopular.ViewHolderDatos>() {
    var listaPopulares:List<Result> = listaPopulares

    // Sirve para enlazar el adaptador con el archivo item_popular creado
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterPopular.ViewHolderDatos {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, null, false)
        return ViewHolderDatos(view)
    }

    // Establece la comunicación entre el adaptador y la clase viewHolderDatos
    override fun onBindViewHolder(holder: AdapterPopular.ViewHolderDatos, position: Int) {
        holder.asignarDatos(listaPopulares.get(position))
    }

    // Regresa e tamaño de la lista
    override fun getItemCount(): Int {
        return listaPopulares.size
    }

    // Se hace referencia al item del layout
   inner class ViewHolderDatos(itemView: View): RecyclerView.ViewHolder(itemView) {
        var imgPopular = itemView.findViewById<ImageView>(R.id.img_popular)
        var txtTitle = itemView.findViewById<TextView>(R.id.txt_title_popular)
        fun asignarDatos(itemPopular:Result){
            var baseUrlimg = "https://image.tmdb.org/t/p/original"
            var uri: Uri = Uri.parse("${baseUrlimg}${itemPopular.poster_path}")
            Glide.with(itemView).load(uri).into(imgPopular)
            txtTitle.setText("${itemPopular.title}")
            /*itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(itemView.context, "${itemPopular.title}", Toast.LENGTH_SHORT).show()

            })*/
            itemView.setOnClickListener {
                listener.onClick(itemPopular.id)
            }
        }
    }
}