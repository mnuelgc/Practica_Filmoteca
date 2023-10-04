package es.ua.eps.filmoteca

class Film {
    var imagesResId = 0 // Propiedades de la clase
    var title : String? = null
    var director: String? = null
    var year = 0
    var genre = 0
    var format =0
    var imdbUrl : String? = null
    var comments : String? = null

    override fun toString(): String { return title?: "<Sin titulo>"} //Al convertir a cadena mostramos su título

    companion object {
        const val FORMAT_DVD = 0 // Formatos
        const val FORMAT_BLURAY = 1 // Formatos
        const val FORMAT_DIGITAL = 2 // Formatos
        const val FORMAT_ACTION = 0 // Generos
        const val FORMAT_COMEDY = 1 // Formatos
        const val FORMAT_DRAMA = 2 // Formatos
        const val FORMAT_SCIFI = 3 // Formatos
        const val FORMAT_HORROR = 4 // Formatos

    }
}