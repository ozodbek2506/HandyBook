package com.example.handybook.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.handybook.Data.BookData
import com.example.handybook.R

class BookAdapter(var list: MutableList<BookData>, var onSelected: OnSelected, var onClick: OnClick): RecyclerView.Adapter<BookAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var img = view.findViewById<ImageView>(R.id.book_img)
        var name = view.findViewById<TextView>(R.id.book_name)
        var author = view.findViewById<TextView>(R.id.author)
        var rating = view.findViewById<TextView>(R.id.reyting)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val a = list[position]
        holder.name.text = a.name
        holder.author.text =a.author
        holder.img.load(a.image)
        holder.rating.text = a.reyting.toString()
        holder.itemView.setOnClickListener {
            onClick.onClick(a)
        }
    }


        interface OnSelected{
        fun onSelected(bookData: BookData)
    }

    interface OnClick{
        fun onClick(bookData: BookData)
    }

}