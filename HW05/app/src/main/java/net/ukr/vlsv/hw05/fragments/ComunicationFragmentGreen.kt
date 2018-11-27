package net.ukr.vlsv.hw05.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.comunication_fragment_green.view.*
import net.ukr.vlsv.hw05.R
import net.ukr.vlsv.hw05.adapters.StatusAdapter
import android.support.v7.widget.LinearLayoutManager

class ComunicationFragmentGreen: Fragment() {
    companion object {
        val STATUS_ARRAY = ArrayList<String>()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.comunication_fragment_green, container, false)
        var statusView = view.status_rv

        statusView.setHasFixedSize(true)                               // если мы уверены, что изменения в контенте не изменят размер layout-а RecyclerView
        statusView.layoutManager = LinearLayoutManager(context)           // используем linear layout manager
        statusView.adapter = StatusAdapter(STATUS_ARRAY)                // создаем адаптер

        return view
    }

    override fun onStart() {
        super.onStart()

        activity!!.title = resources.getText(R.string.app_name_communication)
    }
}