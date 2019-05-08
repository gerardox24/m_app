package pe.edu.upc.iceandfire.network

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import pe.edu.upc.iceandfire.models.Quote
import pe.edu.upc.iceandfire.network.responses.ResponseQuotes
import retrofit2.Call
import retrofit2.http.GET

/*interface GOTQuotesAPI {
    @GET("quotes/")
    fun getQuotes(): Call<Quote>
}*/

class GOTQuotesAPI {
    companion object {
        val baseUrl = "https://got-quotes.herokuapp.com/quotes"

        fun requestGetQuotes( responseHandler: (ResponseQuotes?) -> Unit,
                              errorHandler: (ANError?) -> Unit) {
            AndroidNetworking.get(GOTQuotesAPI.baseUrl)
                .setPriority(Priority.LOW)
                .setTag('G')
                .build()
                .getAsObject(ResponseQuotes::class.java, object : ParsedRequestListener<ResponseQuotes> {
                    override fun onResponse(response: ResponseQuotes?) {
                        responseHandler(response)
                    }

                    override fun onError(anError: ANError?) {
                        errorHandler(anError)
                    }
                })
        }
    }
}