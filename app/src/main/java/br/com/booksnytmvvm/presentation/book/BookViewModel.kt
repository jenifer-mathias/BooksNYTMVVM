package br.com.booksnytmvvm.presentation.book

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.booksnytmvvm.data.model.Book
import br.com.booksnytmvvm.data.response.BookResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel : ViewModel() {

    val bookLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val repository = BookRepository()

    fun getBook(apiKey: String, listName: String) {
        // bookLiveData.value = createFakeBook()
        repository.getBooks(apiKey, listName).enqueue(object : Callback<BookResponse> {

            override fun onResponse(call: Call<BookResponse>, response: Response<BookResponse>) {
                when {
                    response.isSuccessful -> {
                        isLoading.value = false
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookResponse ->
                            for (result in bookResponse.bookResults) {
                                val book = result.bookDetails[0].getBookModel()
                                books.add(book)
                            }
                        }

                        bookLiveData.value = books

                    }
                }
            }

            override fun onFailure(call: Call<BookResponse>, t: Throwable) {
                Log.d("VIEW_MODEL", "error ${t.message}")
            }
        })

        fun createFakeBook(): List<Book> {
            return listOf(
                Book("Title1", "Description1", "Author1", 123.09),
                Book("Title2", "Description2", "Author2", 123.09),
                Book("Title3", "Description3", "Author3", 123.09),
                Book("Title4", "Description4", "Author4", 123.09),
                Book("Title5", "Description5", "Author5", 123.09),
                Book("Title6", "Description6", "Author6", 123.09)
            )
        }

    }
}