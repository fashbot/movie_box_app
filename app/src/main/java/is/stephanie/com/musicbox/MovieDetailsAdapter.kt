package `is`.stephanie.com.musicbox

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class MovieDetailsAdapter(private val movieData: MutableList<String>, private val movieLabels: MutableList<String>) :
    RecyclerView.Adapter<MovieDetailsAdapter.MyViewHolder>() {


    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val movieDetaislTitleView = view.findViewById<TextView>(R.id.card_desc_header)
        val movieDetailsContentView = view.findViewById<TextView>(R.id.card_desc_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailsAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_details, parent, false) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.movieDetaislTitleView.text = movieLabels[position]
        holder.movieDetailsContentView.text = movieData[position]
    }

    override fun getItemCount() = movieData.size
}