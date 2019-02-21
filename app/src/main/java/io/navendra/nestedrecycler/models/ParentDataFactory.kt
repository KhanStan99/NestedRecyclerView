package io.navendra.nestedrecycler.models

import java.util.*
import kotlin.collections.ArrayList

object ParentDataFactory {
    private val random = Random()

    private val titles = arrayListOf("Doctor 1", "Doctor 2", "Doctor 3", "Doctor 4", "Doctor 5")

    private fun randomTitle(): String {
        val index = random.nextInt(titles.size)
        return titles[index]
    }

    private fun randomChildren(): List<ChildModel> {
        return ChildDataFactory.getChildren()
    }

    fun getParents(count: Int): ArrayList<ParentModel> {
        val parents = ArrayList<ParentModel>()
        repeat(count) {
            val parent = ParentModel(0, "Doctor $count", randomChildren())
            parents.add(parent)
        }
        return parents
    }
}