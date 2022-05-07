package dev.mslalith.focuslauncher.data.network.api

import dev.mslalith.focuslauncher.data.network.entities.QuotesApiResponse
import dev.mslalith.focuslauncher.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {
    @GET("/quotes")
    suspend fun getQuotes(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = Constants.Defaults.QUOTES_LIMIT_PER_PAGE,
    ): QuotesApiResponse
}