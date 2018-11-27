package net.ukr.vlsv.hw05.adapters

import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.comunication_fragment_green.view.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.ukr.vlsv.hw05.R

class StatusAdapter (private var mDataset: ArrayList<String>) : RecyclerView.Adapter<StatusAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var mTextView: TextView
        init {
            mTextView = v.findViewById(R.id.status_tw) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.status_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTextView.setText(mDataset[position])
    }

    override fun getItemCount(): Int = mDataset.size
}
