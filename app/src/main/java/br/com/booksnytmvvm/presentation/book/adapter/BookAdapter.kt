package br.com.booksnytmvvm.presentation.book.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.booksnytmvvm.R
import br.com.booksnytmvvm.data.model.Book
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter(val books: List<Book>, val itemClickListener: ((book: Book) -> Unit)) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(itemView, itemClickListener)
    }

    override fun getItemCount() = books.count()

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    class BookViewHolder(itemView: View, private val itemClickListener: ((book: Book) -> Unit)) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.textTitle
        val price = itemView.textPrice

        fun bind(book: Book) {
            title.text = book.title
            price.text = book.price.toString()

            itemView.setOnClickListener {
                itemClickListener.invoke(book)
            }
        }
    }
}