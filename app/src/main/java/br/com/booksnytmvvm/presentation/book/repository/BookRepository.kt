package br.com.booksnytmvvm.presentation.book.repository

import br.com.booksnytmvvm.data.ApiService
import br.com.booksnytmvvm.data.ResultCallback
import br.com.booksnytmvvm.data.response.BookResponse
import retrofit2.Call

interface BookRepository {

//    fun getBooks(apiKey: String, listName: String): Call<BookResponse> {
//        return ApiService.service.getBooks(apiKey, listName)
//    }

    fun getBooks(apiKey: String, listName: String, resultCallBack: (result: ResultCallback) -> Unit)
}