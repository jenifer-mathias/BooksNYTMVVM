package br.com.booksnytmvvm.data.response

import com.google.gson.annotations.SerializedName

data class BookResponse (

    @SerializedName("results")
    val bookResults: List<BookResults>

)