package ac.ic.bookapp.data

import ac.ic.bookapp.model.Book
import ac.ic.bookapp.model.User
import android.content.Context
import kotlinx.coroutines.runBlocking
import retrofit2.http.*

object BookDatasource : Datasource<BookService>(BookService::class.java) {

    fun getBooks(exceptUser: Long? = null): List<Book> {
        return runBlocking {
            service.getBooks(exceptUser)
        }
    }

    fun postBook(isbn: String): Book? {
        return runBlocking {
            try {
                service.postBook(isbn)
            } catch (e: Exception) {
                null
            }
        }
    }

    fun getOwners(bookId: Long): List<User> {
        return runBlocking {
            service.getOwners(bookId)
        }
    }
}

interface BookService {
    @GET("books")
    suspend fun getBooks(@Query("exceptUser") exceptUserId: Long?): List<Book>

    @POST("books")
    suspend fun postBook(@Body isbn: String): Book

    @GET("books/{book_id}/owners")
    suspend fun getOwners(@Path("book_id") bookId: Long): List<User>
}