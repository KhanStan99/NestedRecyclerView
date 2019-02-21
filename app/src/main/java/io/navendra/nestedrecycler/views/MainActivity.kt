package io.navendra.nestedrecycler.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import io.navendra.nestedrecycler.R
import io.navendra.nestedrecycler.models.ChildModel
import io.navendra.nestedrecycler.models.ParentDataFactory
import io.navendra.nestedrecycler.models.ParentModel
import io.navendra.nestedrecycler.models.TimeSlotsModel
import io.navendra.nestedrecycler.views.adapters.ParentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ParentAdapter
    var list: ArrayList<ParentModel> = ParentDataFactory.getParents(4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
    }

    private fun initRecycler() {
        recyclerView = rv_parent
        adapter = ParentAdapter(list, this@MainActivity)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL, false)
        recyclerView.adapter = this.adapter


    }

    fun showToast(title: String) {
        Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
    }

    fun updateChild(parent: ParentModel, parentPosition: Int, b: Boolean) {
        val childModel: ArrayList<ChildModel> = ArrayList()

        val arrayList = ArrayList<TimeSlotsModel>()
        arrayList.add(TimeSlotsModel("Tuesday", "12:30"))
        arrayList.add(TimeSlotsModel("Monday", "02:00"))


        childModel.add(ChildModel(arrayList))

//        for (i in 0 until list[parentPosition].children.size)
//            for (j in 0 until list[parentPosition].children[i].timeSlots.size)
//                Log.e("prev " + list[parentPosition].children[i].timeSlots[j].dayOfWeek, "Posiotion: $parentPosition")

        if (b)
            list[parentPosition].position = parent.position + 1
        else
            list[parentPosition].position = parent.position - 1

        list[parentPosition].title = parent.title
        list[parentPosition].children = childModel

//        for (i in 0 until list[parentPosition].children.size)
//            for (j in 0 until list[parentPosition].children[i].timeSlots.size)
//                Log.e("next " + list[parentPosition].children[i].timeSlots[j].dayOfWeek, "Posiotion: $parentPosition")

        adapter.updateList(list)
    }
}
