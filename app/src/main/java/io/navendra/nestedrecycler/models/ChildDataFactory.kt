package io.navendra.nestedrecycler.models

import java.util.*

object ChildDataFactory {

    fun getChildren(): List<ChildModel> {
        val children = mutableListOf<ChildModel>()

        val arrayList = ArrayList<TimeSlotsModel>()
        arrayList.add(TimeSlotsModel("Saturday", "12:30"))
        arrayList.add(TimeSlotsModel("Saturday", "02:00"))

        arrayList.add(TimeSlotsModel("Sunday", "02:00"))
        arrayList.add(TimeSlotsModel("Sunday", "04:00"))
        val child = ChildModel(arrayList)
        children.add(child)

        return children
    }


}