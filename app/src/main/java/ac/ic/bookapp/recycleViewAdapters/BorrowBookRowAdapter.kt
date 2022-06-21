package ac.ic.bookapp.recycleViewAdapters

import ac.ic.bookapp.R
import ac.ic.bookapp.data.CoverDatasource
import ac.ic.bookapp.data.CoverSize
import ac.ic.bookapp.model.Book
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BorrowBookRowAdapter(
    private val booksList: List<Book>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<BorrowBookRowAdapter.BorrowBookRowViewHolder>() {

    class BorrowBookRowViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.search_row_title)
        val isbnText: TextView = view.findViewById(R.id.search_row_isbn_value)
        val ownersText: TextView = view.findViewById(R.id.search_row_owners_value)
        val icon: ImageView = view.findViewById(R.id.search_row_book_picture)
        lateinit var book: Book
    }

    private val mutableBooksList = booksList.toMutableList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BorrowBookRowViewHolder {
        val adapterView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.row_search, parent, false)
        return BorrowBookRowViewHolder(adapterView)
    }

    override fun onBindViewHolder(holder: BorrowBookRowViewHolder, position: Int) {
        val book = mutableBooksList[position]
        holder.titleText.text = book.title
        holder.book = book
        holder.isbnText.text = book.isbn
        val imgURI = CoverDatasource.getBookCover(book, CoverSize.MEDIUM)
        CoverDatasource.loadCover(holder.icon, imgURI)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(book)
        }
    }

    override fun getItemCount(): Int = mutableBooksList.size

    class OnClickListener(val clickListener: (book: Book) -> Unit) {
        fun onClick(book: Book) = clickListener(book)
    }
}