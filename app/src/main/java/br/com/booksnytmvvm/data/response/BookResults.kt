package br.com.booksnytmvvm.data.response

import com.google.gson.annotations.SerializedName

data class BookResults (

    @SerializedName("book_details")
    val bookDetails: List<BookDetails>
)