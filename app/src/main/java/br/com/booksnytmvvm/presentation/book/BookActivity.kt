package br.com.booksnytmvvm.presentation.book

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.booksnytmvvm.BuildConfig
import br.com.booksnytmvvm.R
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        val viewModel: BookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        viewModel.bookLiveData.observe(this, Observer {
            it?.let { books ->
                with(recycler_view_book) {
                    layoutManager =
                        LinearLayoutManager(this@BookActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BookAdapter(books)

                }
            }
        })

        viewModel.isLoading.observe(this, Observer {
            if (it == true) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })

        viewModel.getBook(BuildConfig.API_KEY, LIST_TYPE)
    }
    companion object{
        private const val LIST_TYPE = "hardcover-fiction"
    }
}

