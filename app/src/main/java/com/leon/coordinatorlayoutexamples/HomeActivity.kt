package com.leon.coordinatorlayoutexamples

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import org.jetbrains.anko.find

class HomeActivity : AppCompatActivity() {

    val listView by lazy { find<ListView>(R.id.listView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOf(
            "tabs and listview",
            "edittext in appbar"
        ))

        listView.setOnItemClickListener { _, _, position, _ ->
            when(position) {
                0 -> startActivity(Intent(this, TabsAndListViewActivity::class.java))
                1 -> startActivity(Intent(this, EditTextInAppBarLayoutActivity::class.java))
            }
        }

    }
}
