package com.example.wergouyaram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wergouyaram.data.model.PharmacieItem

class PharmacyAdapter(private val pharmacyList: List<PharmacieItem>) :
    RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder>() {

    class PharmacyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textViewName: TextView = itemView.findViewById(R.id.nom)
        val textViewPhone: TextView = itemView.findViewById(R.id.tel)
        val textViewAddress: TextView = itemView.findViewById(R.id.adresse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return PharmacyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PharmacyViewHolder, position: Int) {
        val currentItem = pharmacyList[position]
        holder.textViewName.text = currentItem.nom
        holder.textViewPhone.text = currentItem.telephone_fixe
        holder.textViewAddress.text = currentItem.adresse
        // Ajoutez une image appropriée à imageView si nécessaire
    }

    override fun getItemCount() = pharmacyList.size
}
