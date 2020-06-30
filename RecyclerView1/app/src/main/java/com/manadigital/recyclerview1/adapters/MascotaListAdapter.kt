package com.manadigital.recyclerview1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.manadigital.recyclerview1.R
import com.manadigital.recyclerview1.entities.Mascota

class MascotaListAdapter (private var mascotasList : MutableList<Mascota>,val onItemClick : (Int) -> Unit) : RecyclerView.Adapter<MascotaListAdapter.MascotaHolder>() {
//class MascotaListAdapter (private var mascotasList: MutableList<Mascota>) : RecyclerView.Adapter<MascotaListAdapter.MascotaHolder>() {

    companion object {

        private val TAG = "MascotaListAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_mascota,parent,false)
        return (MascotaHolder(
            view
        ))
    }

    override fun getItemCount(): Int {

        return mascotasList.size
    }

    fun setData(newData: ArrayList<Mascota>) {
        this.mascotasList = newData
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MascotaHolder, position: Int) {

        holder.setName(mascotasList[position].nombre)
        holder.getCardLayout().setOnClickListener {
            onItemClick(position)
        }

    }

    class MascotaHolder (v: View) : RecyclerView.ViewHolder(v){

        private var view: View

        init {
            this.view = v
        }

        fun setName(name : String) {
            val txt : TextView = view.findViewById(R.id.txt_name_item)
            txt.text = name
        }

        fun getCardLayout ():CardView{
            return view.findViewById(R.id.card_package_item)
        }

    }

}