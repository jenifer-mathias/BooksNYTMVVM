package br.com.booksnytmvvm.presentation.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.booksnytmvvm.R
import br.com.booksnytmvvm.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter(val books: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(itemView)
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.textTitle
        val author = itemView.textAuthor
        val description = itemView.textDescription
        val price = itemView.textPrice

        fun bind(book: Book) {
            title.text = book.title
            author.text = book.author
            description.text = book.description
            price.text = book.price.toString()
        }
    }
}