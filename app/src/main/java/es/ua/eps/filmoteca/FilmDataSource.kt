package es.ua.eps.filmoteca

class FilmDataSource {

    val films: MutableList<Film> = mutableListOf<Film>()

    init {
        var f = Film()
        f.title = "Regreso al futuro"
        f.director = "Robert Zemeckis"
        f.imagesResId = R.mipmap.ic_launcher
        f.comments  = ""
        f.format = Film.Companion.FORMAT_DIGITAL
        f.genre = Film.Companion.FORMAT_SCIFI
        f.imdbUrl  ="http://www.imdb.com/title/tt0088763"
        f.year = 1985
        films.add(f)

        f = Film()
        f.title = "Spider-man: Cruzando el Multiverso"
        f.director = "Joaquim Dos Santos"
        f.imagesResId = R.drawable.spiderposter
        f.comments  = "Animacion"
        f.format = Film.Companion.FORMAT_DIGITAL
        f.genre = Film.Companion.FORMAT_ACTION
        f.imdbUrl  ="http://www.imdb.com/title/tt9362722"
        f.year = 2023
        films.add(f)

        f = Film()
        f.title = "Scarface"
        f.director = "Brian de Palma"
        f.imagesResId = R.drawable.scarface
        f.comments  = "Mafia"
        f.format = Film.Companion.FORMAT_DVD
        f.genre = Film.Companion.FORMAT_ACTION
        f.imdbUrl  ="http://www.imdb.com/title/tt9362722"
        f.year = 1983
        films.add(f)

    }
}