package `is`.stephanie.com.musicbox

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

class SearchListAdapter(private val myDataset: MutableList<MovieData>, context: Context) :
    RecyclerView.Adapter<SearchListAdapter.MyViewHolder>(){

    private var context = context

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.single_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_search_list, parent, false) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var image = myDataset[position].image
        Picasso.get()
            .load(image)
            .fit()
            .into(holder.imageView)


        holder.imageView.setOnClickListener {
            var intent = Intent(context, MovieDetailsActivity::class.java)
            var data = myDataset[position]
            intent.putExtra(Data.TITLE, data.title)
            intent.putExtra(Data.IMAGE, data.image)
            intent.putExtra(Data.OVERVIEW, data.overview)
            intent.putExtra(Data.ADULT, data.adult)
            intent.putExtra(Data.POPULARITY, data.popularity)
            intent.putExtra(Data.VOTE_AVERAGE, data.voteAverage)
            intent.putExtra(Data.RELEASE_DATE, data.releaseDate)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            Log.d("IMAGEEEE", data.image)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = myDataset.size
}