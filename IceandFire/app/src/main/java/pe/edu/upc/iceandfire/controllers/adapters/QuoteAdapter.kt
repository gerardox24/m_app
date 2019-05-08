package pe.edu.upc.iceandfire.controllers.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import pe.edu.upc.iceandfire.models.Quote
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import pe.edu.upc.iceandfire.R

class QuoteAdapter(var quote: Quote) : RecyclerView.Adapter<QuoteAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val description = itemView.quote_description
        val character = itemView.quote_character
        val btn_init = itemView.btn_init

        fun bindTo(quote: Quote) {
            description.text = quote.quote
            character.text = quote.character
            btn_init.setOnClickListener{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_main, parent, false))
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: QuoteAdapter.ViewHolder, position: Int) {
        holder.bindTo(quote)
    }
}