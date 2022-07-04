package com.example.mol12345

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mol12345.databinding.ItemRecyclerBinding

data class Data1(
    val id : Int,
    val name : String,
    val nick : String,
    val number : String,
)

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.MyViewHolder>(){
    var data = mutableListOf<Data1>()
    private var _bb : ItemRecyclerBinding? = null
    private val bbinding get() = _bb!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _bb = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val binding = bbinding
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }
    inner class MyViewHolder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(layoutData: Data1) {
            binding.name.text = layoutData.name
            val context = binding.name.context
            binding.name.setOnClickListener {
                val intent = Intent(context, ContactActivity::class.java)
                intent.putExtra("Human_name",layoutData.name)
                intent.putExtra("Human_nick",layoutData.nick)
                intent.putExtra("Phone_number",layoutData.number)
                context.startActivity(intent)
            }
        }
    }
}