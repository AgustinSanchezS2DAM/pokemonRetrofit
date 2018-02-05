package net.azarquiel.retrofixinirx.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row.view.*
import net.azarquiel.retrofixinirx.model.Result

/**
 * Created by pacopulido on 02/11/17.
 */
class CustomAdapterPokemon(val context: Context,
                           val layout: Int,
                           val dataList: List<Result>
                    ): RecyclerView.Adapter<CustomAdapterPokemon.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item,position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {


        fun bind(dataItem: Result, position: Int){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            itemView.tvname.text=dataItem.name
        }

    }

}