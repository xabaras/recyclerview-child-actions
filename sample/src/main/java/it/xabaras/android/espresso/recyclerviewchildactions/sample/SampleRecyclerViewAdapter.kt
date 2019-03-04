package it.xabaras.android.espresso.recyclerviewchildactions.sample

import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_list_item.view.*

class SampleRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = holder.itemView.context.getString(R.string.row_text, position+1)
        holder.txtDescription.setText(text)
        holder.btnShow.setOnClickListener {
            AlertDialog.Builder(it.context)
                .setTitle(R.string.app_name)
                .setMessage(holder.txtDescription.text)
                .setNeutralButton(android.R.string.ok, null)
                .create()
                .show()
        }
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val txtDescription = view.txtDescription
    val btnShow = view.btnAlert
}