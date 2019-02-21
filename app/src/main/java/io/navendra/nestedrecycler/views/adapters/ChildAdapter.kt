package io.navendra.nestedrecycler.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import io.navendra.nestedrecycler.R
import io.navendra.nestedrecycler.views.MainActivity

class ChildAdapter(private val heading: String,
                   private val child: ArrayList<String>,
                   private val mainActivity: MainActivity)
    : StatelessSection(SectionParameters.builder()
        .headerResourceId(R.layout.header_layout)
        .itemResourceId(R.layout.child_recycler)
        .build()) {

    override fun getContentItemsTotal(): Int {
        return child.size
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        holder as HeaderViewHolder
        holder.txtHeading.text = heading
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder as ItemViewHolder
        holder.txtQuestion.text = child[position]

//        holder.txtQuestion.setOnClickListener {
//            mainActivity.showToast("Child: " + child[position] + " Position $position")
//        }
    }

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    private class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtHeading: TextView = view.findViewById(R.id.txt_heading) as TextView
    }

    private class ItemViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val txtQuestion: TextView = rootView.findViewById(R.id.child_textView)
    }
}