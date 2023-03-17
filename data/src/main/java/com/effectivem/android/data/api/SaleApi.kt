package com.effectivem.android.data.api

import retrofit2.http.GET

interface SaleApi {
    @GET(
        "/v3/a9ceeb6e-416d-4352-bde6-2203416576ac"
    )
    suspend fun fetch(): SaleResponse
}