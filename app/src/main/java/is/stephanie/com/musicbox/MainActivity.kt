package `is`.stephanie.com.musicbox

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest


class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    var list: MutableList<MovieData> = mutableListOf()
    var mAdapter = SearchListAdapter(list, this@MainActivity)
    lateinit var title: String
    lateinit var image: String
    lateinit var overview: String
    lateinit var adult: String
    lateinit var popularity: String
    lateinit var voteAverage: String
    lateinit var releaseDate: String
    lateinit var image_path: String
    lateinit var newMovie: MovieData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MusicRequests.getInstance(this.applicationContext).requestQueue

        getMovieData("")
        checkIfEmptyData(getString(R.string.search_message))

        updateSearch()

        recyclerView = findViewById<RecyclerView>(R.id.search_artist_list).apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context.applicationContext, 2)
            adapter = mAdapter
        }
    }

    private fun getMovieData(searchTerm: String) {

        var searchString = Data.MOVIE_API + searchTerm
        list.clear()
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, searchString, null,
            Response.Listener { response ->
                var result = response.getJSONArray("results")
                var data = (result.length() - 1)
                for (i in 0..data) {
                    image_path = result.getJSONObject(i).getString(Data.POSTER_PATH)

                    title = result.getJSONObject(i).getString(Data.TITLE)
                    image = Data.MOVIE_IMAGE_API + image_path
                    overview = result.getJSONObject(i).getString(Data.OVERVIEW)
                    adult = result.getJSONObject(i).getString(Data.ADULT)
                    popularity = result.getJSONObject(i).getString(Data.POPULARITY)
                    voteAverage = result.getJSONObject(i).getString(Data.VOTE_AVERAGE)
                    releaseDate = result.getJSONObject(i).getString(Data.RELEASE_DATE)
                    newMovie = MovieData(title, image, overview, adult, popularity, voteAverage, releaseDate)
                    if (image_path !== "null") {
                        list.add(newMovie)
                    }

                }
                mAdapter.notifyDataSetChanged()
                checkIfEmptyData(getString(R.string.no_movies_message))

            },
            Response.ErrorListener { error ->
                Log.e("ERRORTEXT:", error.toString())
            }
        )
        MusicRequests.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }


    private fun updateSearch() {
        var editText: EditText = findViewById(R.id.main_text)
        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.getAction() === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                var searchTerm = editText.text.toString()
                getMovieData(searchTerm)
                hideKeyboard()
                return@OnKeyListener true
            }
            false
        })
    }

    private fun hideKeyboard() {
        val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
    }

    private fun checkIfEmptyData(message: String) {
        var emptyView = findViewById<TextView>(R.id.empty_view)
        emptyView.visibility = View.GONE
        if (mAdapter.itemCount == 0) {
            emptyView.text = message
            emptyView.visibility = View.VISIBLE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }
}

