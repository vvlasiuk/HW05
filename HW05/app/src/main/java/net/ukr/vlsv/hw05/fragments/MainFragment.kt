package net.ukr.vlsv.hw05.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import net.ukr.vlsv.hw05.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        val sysInfo_btn_: Button = view.findViewById(R.id.sysInfo_btn)
        sysInfo_btn_.setOnClickListener{
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.main_fl, SystemInfoFragment())
                .addToBackStack(null)
                .commit()
        }

        val fragCommun_btn_: Button = view.findViewById(R.id.fragCommun_btn)
        fragCommun_btn_.setOnClickListener{
//            activity!!.supportFragmentManager.beginTransaction()
            fragmentManager!!.beginTransaction()
                .replace(R.id.main_fl, ComunicationFragment())
                .addToBackStack(null)
                .commit()
        }


        return view
    }

    override fun onStart() {
        super.onStart()

        (activity as net.ukr.vlsv.hw05.MainActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        activity!!.title = resources.getText(R.string.app_name)
    }
}