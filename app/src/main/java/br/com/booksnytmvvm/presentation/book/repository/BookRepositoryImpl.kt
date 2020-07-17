package br.com.booksnytmvvm.presentation.book.repository

import br.com.booksnytmvvm.data.ApiService
import br.com.booksnytmvvm.data.ResultCallback
import br.com.booksnytmvvm.data.model.Book
import br.com.booksnytmvvm.data.response.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepositoryImpl : BookRepository {
    override fun getBooks(
        apiKey: String,
        listName: String,
        resultCallBack: (result: ResultCallback) -> Unit
    ) {
        ApiService.service.getBooks(apiKey, listName).enqueue(object : Callback<BookResponse> {

            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookResponse ->
                            for (result in bookResponse.bookResults) {
                                val book = result.bookDetails[0].getBookModel()
                                books.add(book)
                            }
                        }
                        resultCallBack(ResultCallback.Success(books))
                    }
                    response.code() == 401 -> resultCallBack(ResultCallback.ApiError(401))
                    else -> resultCallBack(ResultCallback.ApiError(400))
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                resultCallBack(ResultCallback.ServerError)
            }

        })
    }


}