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

class AdapterEstreno(lista: List<Result>, private var listener: OnClickListener) :
    RecyclerView.Adapter<AdapterEstreno.ViewHolderDatos>() {

    var listaResult: List<Result> = lista

    // Sirve para enlazar el adaptador con el archivo item_estreno creado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatos {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_estreno, null, false)
        return ViewHolderDatos(view)
    }

    // Establece la comunicación entre el adaptador y la clase ViewHolderDatos
    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) {
        holder.asignarDatos(listaResult.get(position))
    }

    // Regresa el tamaño de la lista
    override fun getItemCount(): Int {
        return listaResult.size
    }

    // Se hace referencia al item del layout
    inner class ViewHolderDatos(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgEstreno = itemView.findViewById<ImageView>(R.id.img_estreno)
        var txtTitle = itemView.findViewById<TextView>(R.id.txt_title_estreno)

        fun asignarDatos(itemEstreno: Result) {
            var baseUrlimg = "https://image.tmdb.org/t/p/original"
            var uri:Uri = Uri.parse("${baseUrlimg}${itemEstreno.poster_path}")
            Glide.with(itemView).load(uri).into(imgEstreno)
            txtTitle.text = itemEstreno.title
            /*itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(itemView.context, "${itemEstreno.id}-${itemEstreno.title}", Toast.LENGTH_SHORT).show()


            })*/
            itemView.setOnClickListener{
                listener.onClick(itemEstreno.id)
            }
        }
    }
}