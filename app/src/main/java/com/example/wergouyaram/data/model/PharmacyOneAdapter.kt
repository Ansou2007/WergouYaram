package com.example.wergouyaram.data.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wergouyaram.R

class PharmacyOneAdapter(private val pharmacyList: List<PharmacieItem>) :
    RecyclerView.Adapter<PharmacyOneAdapter.PharmacyViewHolder>() {

    class PharmacyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.nom)
        val textViewPhone: TextView = itemView.findViewById(R.id.tel)
        val textViewAddress: TextView = itemView.findViewById(R.id.adresse)
        val textViewEmpty: TextView = itemView.findViewById(R.id.empty_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        return PharmacyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PharmacyViewHolder, position: Int) {
        if (pharmacyList.isEmpty()) {
            holder.textViewName.visibility = View.GONE
            holder.textViewPhone.visibility = View.GONE
            holder.textViewAddress.visibility = View.GONE
            holder.textViewEmpty.visibility = View.VISIBLE
            holder.textViewEmpty.text = "Aucune pharmacie disponible"
        } else {
            val currentItem = pharmacyList[position]
            holder.textViewName.text = currentItem.nom ?: "Nom non disponible"
            holder.textViewPhone.text = currentItem.telephone_portable ?: "Téléphone non disponible"
            holder.textViewAddress.text = currentItem.adresse ?: "Adresse non disponible"
            holder.textViewEmpty.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return pharmacyList.size
    }
}
