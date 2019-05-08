package pe.edu.upc.iceandfire.controllers.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.upc.iceandfire.R
import pe.edu.upc.iceandfire.controllers.adapters.QuoteAdapter
import pe.edu.upc.iceandfire.models.Quote
import pe.edu.upc.iceandfire.network.GOTQuotesAPI
import pe.edu.upc.iceandfire.network.responses.ResponseQuotes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

 class MainActivity : AppCompatActivity() {

    lateinit var quoteAdapter: QuoteAdapter
    lateinit var service : GOTQuotesAPI


    val TAG = "GOT"

     private var quote = ""
     private var quoteText:TextView? = null
     private var quoteCharacter:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getQuote()

        quoteText = findViewById(R.id.quote_description)
        quoteCharacter = findViewById(R.id.quote_character)

        btn_init.setOnClickListener{
            startActivity(Intent(this, Main2Activity::class.java))
        }
    }

     fun getQuote() {
         AndroidNetworking.get("https://got-quotes.herokuapp.com/quotes")
             .addQueryParameter("char", "tyrion")
             .setPriority(Priority.LOW)
             .build()
             .getAsObject(
                 ResponseQuotes::class.java ,
                 object: ParsedRequestListener<ResponseQuotes> {
                     override fun onResponse(response: ResponseQuotes?) {
                         response!!.quote.apply{
                             quote = this
                             quoteText!!.setText(quote)
                             Log.d("respuesta", "Char ${this}")
                         }
                         response!!.character.apply {
                             quote = this
                             quoteCharacter!!.setText(quote)
                         }
                     }

                     override fun onError(anError: ANError?) {
                         anError?.apply {
                             Log.d("IceFire", message)
                         }
                     }
                 })
     }

     /*val retrofit: Retrofit = Retrofit.Builder()
           .baseUrl("https://got-quotes.herokuapp.com/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()

       service = retrofit.create<GOTQuotesAPI>(GOTQuotesAPI::class.java)*/

     /*service.getQuotes().enqueue(object: Callback<Quote> {
         override fun onResponse(call: Call<Quote>?, response: Response<Quote>?) {
         }
     })*/

     /*GOTQuotesAPI.apply {
         requestGetQuotes({
             it?.apply {
                 Log.d("",this.quote)
                 Log.d("",this.character)
                 quoteAdapter.quote
                 quoteAdapter.notifyDataSetChanged()
             }
         },{

         })
     }*/
}
