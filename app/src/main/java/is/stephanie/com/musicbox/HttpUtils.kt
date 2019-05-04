package `is`.stephanie.com.musicbox

import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class HttpUtils {

    companion object {
        lateinit var value: String
        val mainUrl =  "http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=$value&api_key=f5d7a2fa575f5ca87431b16214e6e085&format=json"
    }

    fun getUrl(search: String): String{
        value = search
        return mainUrl
    }
}