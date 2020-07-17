package br.com.booksnytmvvm.presentation.book.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.booksnytmvvm.BuildConfig
import br.com.booksnytmvvm.data.ResultCallback
import br.com.booksnytmvvm.data.model.Book
import br.com.booksnytmvvm.presentation.book.repository.BookRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.http.GET
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class BookViewModelTest {

//    @Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//    }     Outra forma de inicializar variaveis

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var bookLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var errorObserver: Observer<Int>

    @Test
    fun `when ViewModel Get Books Get Success Then Sets booksLiveData` () {
        //arrange
        val books = listOf(Book("Title 1", "Description 1", "Author 1", 10.00))
        val resultSuccess = MockBockRepositoryImpl(ResultCallback.Success(books))
        val viewModel = BookViewModel(resultSuccess)
        viewModel.bookLiveData.observeForever(bookLiveDataObserver)

        //act
        viewModel.getBook(BuildConfig.API_KEY, "hardcover-fiction")

        //assert
        //verify(bookLiveDataObserver).onChanged(listOf())
       // verify(bookLiveDataObserver).onChanged(listOf(Book("Title 2", "Description 1", "Author 1", 10.00)))
        verify(bookLiveDataObserver).onChanged(books)
    }

    @Test
    fun `when viewmodel getbooks get error 400 then sets log d`() {
        //arrange
        val error400 = MockBockRepositoryImpl(ResultCallback.ApiError(400))
        val viewModel = BookViewModel(error400)
        viewModel.error.observeForever(errorObserver)

        //act
        viewModel.getBook(BuildConfig.API_KEY, "hardcover-fiction")

        //assert
        verify(errorObserver).onChanged(400)
    }

    class MockBockRepositoryImpl(private val result: ResultCallback) : BookRepository {
        override fun getBooks(apiKey: String, listName: String, resultCallBack: (result: ResultCallback) -> Unit) {
            resultCallBack(result)
        }

    }
}