package net.ukr.vlsv.hw05.fragments

import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.ToggleButton
import kotlinx.android.synthetic.main.comunication_fragment.*
import kotlinx.android.synthetic.main.comunication_fragment_blue.*
import kotlinx.android.synthetic.main.comunication_fragment_green.*
import kotlinx.android.synthetic.main.comunication_fragment_green.view.*
import net.ukr.vlsv.hw05.R
import kotlin.contracts.Returns

class ComunicationFragmentBlue: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.comunication_fragment_blue, container, false)

        val pressButton_tbtn_: ToggleButton = view.findViewById(R.id.pressButton_tbtn)
        pressButton_tbtn_.setOnClickListener{
            val textPressed: String
            if (pressButton_tbtn_.isChecked) textPressed = "Pressed" else textPressed = "Not pressed"

            ComunicationFragmentGreen.STATUS_ARRAY.add("Button state: \"$textPressed\"")
            fragment_green_fl.status_rv.adapter!!.notifyDataSetChanged()
        }

        val switch_sw_: Switch = view.findViewById(R.id.switch_sw)
        switch_sw_.setOnCheckedChangeListener{buttonView, isChecked ->
            val textPressed: String
            if (isChecked) textPressed = "On" else textPressed = "Off"

            ComunicationFragmentGreen.STATUS_ARRAY.add("Switch: \"$textPressed\"")
            fragment_green_fl.status_rv.adapter!!.notifyDataSetChanged()
        }

        val text_et_: EditText = view.findViewById(R.id.text_et)
        text_et_.setOnEditorActionListener{v, keyCode, event ->
            ComunicationFragmentGreen.STATUS_ARRAY.add("Text: \"${text_et_.text}\"")
            fragment_green_fl.status_rv.adapter!!.notifyDataSetChanged()

            text_et_.setText("")

            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
            return@setOnEditorActionListener true
        }


        return view
    }

    override fun onStart() {
        super.onStart()

    }
}