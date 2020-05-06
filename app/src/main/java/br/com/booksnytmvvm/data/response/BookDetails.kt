package br.com.booksnytmvvm.data.response

import br.com.booksnytmvvm.data.model.Book
import com.google.gson.annotations.SerializedName

data class BookDetails(

    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("price")
    val price: Double
) {
    fun getBookModel() = Book(
        title = this.title,
        description = this.description,
        author = this.author,
        price = this.price
    )
}