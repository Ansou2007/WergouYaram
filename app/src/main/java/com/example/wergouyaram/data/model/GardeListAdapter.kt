package com.example.wergouyaram.data.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wergouyaram.PharmacyAdapter
import com.example.wergouyaram.R

class GardeListAdapter(private val GardeList: List<GardesItem>) :

    RecyclerView.Adapter<GardeListAdapter.GardeViewHolder>() {

    class GardeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      //  val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val nom: TextView = itemView.findViewById(R.id.nom)
        val telephone: TextView = itemView.findViewById(R.id.tel)
        val adresse: TextView = itemView.findViewById(R.id.adresse)
        val date_debut : TextView = itemView.findViewById(R.id.date_debut)
        val date_fin : TextView = itemView.findViewById(R.id.date_fin)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_liste_garde, parent, false)
        return GardeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GardeViewHolder, position: Int) {
        val currentItem = GardeList[position]
        holder.nom.text = currentItem.pharmacie.nom
        holder.date_debut.text = currentItem.date_debut
        holder.date_fin.text = currentItem.date_fin
        holder.adresse.text = currentItem.pharmacie.adresse
        holder.telephone.text = currentItem.pharmacie.telephone
       // holder.textViewAddress.text = currentItem.adresse
        // Ajoutez une image appropriée à imageView si nécessaire
    }

    override fun getItemCount() = GardeList.size
}

