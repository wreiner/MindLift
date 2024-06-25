package eu.sumindlift.mindlift.api

import retrofit2.http.GET

interface ApiService {
    @GET("quotes")
    suspend fun getQuotes(): List <Quote>
}