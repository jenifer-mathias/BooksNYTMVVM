package br.com.booksnytmvvm.presentation.book.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.booksnytmvvm.BuildConfig
import br.com.booksnytmvvm.R
import br.com.booksnytmvvm.presentation.base.BaseActivity
import br.com.booksnytmvvm.presentation.book.adapter.BookAdapter
import br.com.booksnytmvvm.presentation.book.repository.BookRepositoryImpl
import br.com.booksnytmvvm.presentation.book.viewmodel.BookViewModel
import br.com.booksnytmvvm.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.toolbar.*

class BookActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        setupToolbar(toolbarBook, R.string.name_toolbar_book_activity, false)

//        val viewModel: BookViewModel = ViewModelProviders.of(this).get(
//            BookViewModel::class.java
//        )

        val viewModel: BookViewModel = BookViewModel.ViewModelFactory(BookRepositoryImpl()).create(BookViewModel::class.java)

        viewModel.bookLiveData.observe(this, Observer {
            it?.let { books ->
                with(recycler_view_book) {
                    layoutManager =
                        LinearLayoutManager(this@BookActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BookAdapter(books) { book ->
                        val intent = Intent(this@BookActivity, DetailActivity::class.java)
                        intent.putExtra("STRING_AUTHOR", book.author)
                        intent.putExtra("STRING_DESCRIPTION", book.description)
                        startActivity(intent)
                    }
                }
            }
        })

        viewModel.isLoading.observe(this, Observer {
            if (it) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })

//        viewModel.error.observe(this, Observer {
//            if (it) {
//                Toast.makeText(this@BookActivity, "Falha no carregamento. Tente mais tarde.", Toast.LENGTH_SHORT).show()
//            }
//        })

        viewModel.getBook(
            BuildConfig.API_KEY,
            LIST_TYPE
        )
    }

    companion object {
        private const val LIST_TYPE = "hardcover-fiction"
    }
}

