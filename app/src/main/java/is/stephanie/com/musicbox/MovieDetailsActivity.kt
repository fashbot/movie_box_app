package `is`.stephanie.com.musicbox

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var  singleMovieRecyclerView: RecyclerView
    private lateinit var singleMovieAdapter: MovieDetailsAdapter
    private var movieData : MutableList<String> = mutableListOf()
    private var movieLabels : MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)

        getData()

        singleMovieRecyclerView = findViewById<RecyclerView>(R.id.single_movie_recyclerview).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context.applicationContext)
            singleMovieAdapter = MovieDetailsAdapter(movieData, movieLabels)
            adapter = singleMovieAdapter
        }

    }

    private fun getData(){

        var image = intent.extras?.getString(Data.IMAGE)!!
        var title = intent.extras?.getString(Data.TITLE)!!
        var overview = intent.extras?.getString(Data.OVERVIEW)!!
        var adult = intent.extras?.getString(Data.ADULT)!!
        var popularity = intent.extras?.getString(Data.POPULARITY)!!
        var voteAverage = intent.extras?.getString(Data.VOTE_AVERAGE)!!
        var releaseDate = intent.extras?.getString(Data.RELEASE_DATE)!!

        populateImage(image)
        var dataArray = listOf(title, overview, adult, popularity, voteAverage, releaseDate)
        var labelArray = listOf(Data.TITLE, Data.OVERVIEW, Data.ADULT, Data.POPULARITY, Data.VOTE_AVERAGE, Data.RELEASE_DATE)
        movieData.addAll(dataArray)
        movieLabels.addAll(labelArray)

    }

    private fun populateImage(imageLink: String){

        var imageView = findViewById<ImageView>(R.id.single_image)
        var image = imageLink
        Picasso.get()
            .load(image)
            .fit()
            .into(imageView)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }
}
