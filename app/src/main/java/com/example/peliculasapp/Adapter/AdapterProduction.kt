package com.example.peliculasapp.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculasapp.ObjetoPelicula.ProductionCompany
import com.example.peliculasapp.R

class AdapterProduction(lista:List<ProductionCompany>):RecyclerView.Adapter<AdapterProduction.ViewHolderDatos>() {
    var lista:List<ProductionCompany> = lista

    // Sirve para enlazar el adaptador con el archivo item creado
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatos {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.company_production, null, false)
        return ViewHolderDatos(view)
    }

    // Establece la comunicación entre el adaptador y la clase viewHolderDatos
    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) {
        holder.asignarDatos(lista.get(position))
    }

    // Regresa e tamaño de la lista
    override fun getItemCount(): Int {
        return lista.size
    }

    // Se hace referencia al item del layout
    inner class ViewHolderDatos(itemView: View):RecyclerView.ViewHolder(itemView){
        var imgProd = itemView.findViewById<ImageView>(R.id.imgPhotoProd)
        var textProd = itemView.findViewById<TextView>(R.id.txtNameProd)
        fun asignarDatos(item:ProductionCompany){
            var baseUrlimg = "https://image.tmdb.org/t/p/original"
            var uri: Uri = Uri.parse("${baseUrlimg}${item.logo_path}")
            var uriNoImage = Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQO6JPHK1VkhnXyF-JblYmBWNQqOsJ1p9AL1JmjnQABCNV3g3Vbn3ILskTCCti96pggJfc&usqp=CAU")

            if(item.logo_path == null){
                Glide.with(itemView).load(uriNoImage).into(imgProd)
            }else{
                Glide.with(itemView).load(uri).into(imgProd)
            }

            textProd.setText(item.name)
        }
    }
}