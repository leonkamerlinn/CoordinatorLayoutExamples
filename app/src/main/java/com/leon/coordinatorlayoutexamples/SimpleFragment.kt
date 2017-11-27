package com.leon.coordinatorlayoutexamples


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.find


/**
 * Created by Leon on 29.10.2017..
 */
class SimpleFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_simple, container, false) as View

        return rootView
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.find<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = RecyclerViewAdapter(view.context)
        recyclerView.itemAnimator = DefaultItemAnimator()

    }






}