package br.com.booksnytmvvm.presentation.book.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.booksnytmvvm.data.ResultCallback
import br.com.booksnytmvvm.data.model.Book
import br.com.booksnytmvvm.presentation.book.repository.BookRepository
import java.lang.IllegalArgumentException
import kotlin.math.log

class BookViewModel(val repository: BookRepository) : ViewModel() {

    val bookLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<Int> = MutableLiveData()

    fun getBook(apiKey: String, listName: String) {
       repository.getBooks(apiKey, listName) {callBack: ResultCallback ->
           when(callBack) {
               is ResultCallback.Success -> {
                  bookLiveData.value = callBack.books
               }
               is ResultCallback.ApiError -> {
                   if (callBack.statusCode == 400) {
                       //Log.d("VIEWMODEL", "StatusCode ${callBack.statusCode}")
                       error.value = callBack.statusCode
                   }
               }
               is ResultCallback.ServerError -> {
                   Log.d("VIEWMODEL", "")
               }
           }
       }
    }

    class ViewModelFactory(val repository: BookRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
                return BookViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknow viewModel class")
        }

    }

}