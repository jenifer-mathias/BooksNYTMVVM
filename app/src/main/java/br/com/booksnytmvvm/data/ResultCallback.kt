package br.com.booksnytmvvm.data

import br.com.booksnytmvvm.data.model.Book

sealed class ResultCallback {
    class Success(val books: List<Book>) : ResultCallback()
    class ApiError(val statusCode: Int) : ResultCallback()
    object ServerError : ResultCallback()
}