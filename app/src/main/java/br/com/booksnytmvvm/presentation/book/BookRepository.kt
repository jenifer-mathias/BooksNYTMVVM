package br.com.booksnytmvvm.presentation.book

import br.com.booksnytmvvm.data.ApiService
import br.com.booksnytmvvm.data.response.BookResponse
import retrofit2.Call

class BookRepository {

    fun getBooks(apiKey: String, listName: String): Call<BookResponse> {
        return ApiService.service.getBooks(apiKey, listName)
    }
}