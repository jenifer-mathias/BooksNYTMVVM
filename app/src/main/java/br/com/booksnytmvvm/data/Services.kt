package br.com.booksnytmvvm.data

import br.com.booksnytmvvm.data.response.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

 @GET("lists.json")
 fun getBooks(@Query("api-key") apiKey: String, @Query("list") list: String) : Call<BookResponse>

}