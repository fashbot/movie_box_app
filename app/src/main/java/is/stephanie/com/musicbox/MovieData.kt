package `is`.stephanie.com.musicbox

class MovieData(
    var title: String,
    var image: String,
    var overview: String,
    var adult: String,
    var popularity: String,
    var voteAverage: String,
    var releaseDate: String
){

    init{
        this.title = title
        this.image = image
        this.overview = overview
        this.adult = adult
        this.popularity = popularity
        this.voteAverage = voteAverage
        this.releaseDate = releaseDate
    }

}

