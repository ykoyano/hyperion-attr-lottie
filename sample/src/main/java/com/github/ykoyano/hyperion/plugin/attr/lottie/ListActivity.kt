package com.github.ykoyano.hyperion.plugin.attr.lottie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {

    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: RecyclerView.Adapter<*>
    lateinit var recyclerView: RecyclerView

    private var data: List<String> = listOf(
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "JellyBean",
            "Kitkat",
            "Lollipop",
            "Marshmallow",
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "JellyBean",
            "Kitkat",
            "Lollipop",
            "Marshmallow"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        adapter = CustomAdapter(data)

        layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.also {
            it.setHasFixedSize(true)
            it.layoutManager = layoutManager
            it.itemAnimator = DefaultItemAnimator()
            it.adapter = adapter
        }
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, ListActivity::class.java)
        }
    }
}
