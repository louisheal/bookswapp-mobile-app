package ac.ic.bookapp

import ac.ic.bookapp.data.Datasource
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ac.ic.bookapp.databinding.ActivityAddBookBinding
import ac.ic.bookapp.model.Book
import android.content.Intent
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import kotlinx.coroutines.runBlocking
import ac.ic.bookapp.MainActivity as MainActivity

class AddBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        binding.addBookButton.setOnClickListener { addMyBook(binding.isbnEditText.text.toString()) }
        binding.scanBookButton.setOnClickListener {
            val intent = Intent(this, ScannerViewActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun addMyBook(isbn: String) {
        if (Datasource.postBook(isbn).code == 200) {
            Toast.makeText(applicationContext, "Book Added!", LENGTH_SHORT).show()
            // TODO: starts MainActivity, might not be MyBooks when there are more
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(applicationContext, "Error", LENGTH_SHORT).show()
        }
    }
}
