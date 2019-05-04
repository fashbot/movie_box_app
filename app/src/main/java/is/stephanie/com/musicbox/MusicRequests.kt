package `is`.stephanie.com.musicbox

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONTokener

class MusicRequests constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: MusicRequests? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MusicRequests(context).also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun addToRequestQueue(request: JsonObjectRequest){
        requestQueue.add(request)
    }

}


