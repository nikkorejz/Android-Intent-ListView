package com.example.secondpracticeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast

class MyAdapter(val list: List<Contact>): BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(idx: Int): Contact {
        return list[idx]
    }

    override fun getItemId(idx: Int): Long {
        return idx.toLong()
    }

    override fun getView(idx: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder

        if (convertView == null) {
            viewHolder = ViewHolder()
            val view = LayoutInflater.from(parent?.context).inflate(
                R.layout.list_view_element, parent, false)
            viewHolder.parent = view
//            viewHolder.parent.setOnClickListener {
//                Toast.makeText(
//                    parent?.context,
//                    idx.toString(), Toast.LENGTH_SHORT
//                ).show()
//            }
            viewHolder.nameText = view.findViewById(R.id.TextName)
            viewHolder.phoneText = view.findViewById(R.id.TextPhone)
        } else {
            viewHolder = convertView.tag as ViewHolder
        }

        viewHolder.nameText.text = getItem(idx).name
        viewHolder.phoneText.text = getItem(idx).phone
        return viewHolder.parent
    }

    class ViewHolder {
        lateinit var parent: View
        lateinit var nameText: TextView
        lateinit var phoneText: TextView
    }

}