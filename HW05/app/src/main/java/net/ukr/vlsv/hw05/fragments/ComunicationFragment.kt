package net.ukr.vlsv.hw05.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.ukr.vlsv.hw05.R

class ComunicationFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.comunication_fragment, container, false)

        return view
    }

    override fun onStart() {
        super.onStart()

        (activity as net.ukr.vlsv.hw05.MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        activity!!.title = resources.getText(R.string.app_name_communication)
    }
}