package io.navendra.nestedrecycler.views.adapters

import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import io.navendra.nestedrecycler.R
import io.navendra.nestedrecycler.models.ParentModel
import io.navendra.nestedrecycler.views.MainActivity
import kotlinx.android.synthetic.main.parent_recycler.view.*

class ParentAdapter(private var parents: ArrayList<ParentModel>,
                    private val mainActivity: MainActivity) : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.parent_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = parents[position].title

        holder.textView.setOnClickListener {
            mainActivity.showToast("Child: " + parents[position].title + " Position $position")
        }

        for (i in 0 until parents[position].children.size)
            for (j in 0 until parents[position].children[i].timeSlots.size)
                Log.e("PREV", parents[position].children[i].timeSlots[j].dayOfWeek)

        holder.recyclerView.apply {
            val glm = GridLayoutManager(context, 4)
            glm.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (holder.adapter.getSectionItemViewType(position)) {
                        SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER -> 4
                        else -> 1
                    }
                }
            }
            layoutManager = glm
            adapter = holder.adapter
        }

        var perDaySlots = ArrayList<String>()
        perDaySlots.clear()
        val parentMainDateTimeModel = parents[position].children

        holder.adapter.removeAllSections()

        for (i in 0 until parentMainDateTimeModel.size) {
            val currentWeekModel = parentMainDateTimeModel[i].timeSlots
            for (k in 0 until currentWeekModel.size) {
                val currentTimeModel = currentWeekModel[k]

                val currentDay = currentTimeModel.dayOfWeek
                var nextDay = ""

                if (k + 1 < currentWeekModel.size)
                    nextDay = currentWeekModel[k + 1]
                            .dayOfWeek

                if (currentDay == nextDay) {
                    perDaySlots.add(currentTimeModel.time)
                    if (i == currentWeekModel.size - 1) {
                        holder.adapter.addSection(ChildAdapter(currentTimeModel.dayOfWeek, perDaySlots, mainActivity))
                        perDaySlots = ArrayList()
                    }
                } else {
                    perDaySlots.add(currentTimeModel.time)
                    holder.adapter.addSection(ChildAdapter(currentTimeModel.dayOfWeek, perDaySlots, mainActivity))
                    perDaySlots = ArrayList()
                }
            }
        }

//        for (i in 0 until parents[position].children.size)
//            for (j in 0 until parents[position].children[i].timeSlots.size)
//                Log.e("NEXT", parents[position].children[i].timeSlots[j].dayOfWeek)

        if (parents[position].position <= 0)
            holder.prevTextView.setTextColor(ContextCompat.getColor(mainActivity.applicationContext, R.color.colorAccentDim))
        else
            holder.prevTextView.setTextColor(ContextCompat.getColor(mainActivity.applicationContext, R.color.colorAccent))

        if (parents[position].position <= 3)
            holder.nextTextView.setTextColor(ContextCompat.getColor(mainActivity.applicationContext, R.color.colorAccent))
        else
            holder.nextTextView.setTextColor(ContextCompat.getColor(mainActivity.applicationContext, R.color.colorAccentDim))

        holder.nextTextView.setOnClickListener {
            if (parents[position].position <= 3) {
                perDaySlots = ArrayList()
                mainActivity.updateChild(parents[position], position, true)
            } else {
                mainActivity.showToast("Nope")
            }
        }

        holder.prevTextView.setOnClickListener {
            if (parents[position].position > 0) {
                perDaySlots = ArrayList()
                mainActivity.updateChild(parents[position], position, false)
            } else {
                mainActivity.showToast("Nope")
            }
        }

    }

    fun updateList(list: ArrayList<ParentModel>) {
        this.parents = list
        notifyDataSetChanged()

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recyclerView: RecyclerView = itemView.rv_child
        val textView: TextView = itemView.textView
        val prevTextView: TextView = itemView.txt_prev_slots
        val nextTextView: TextView = itemView.txt_next_slots
        val adapter = SectionedRecyclerViewAdapter()
    }
}