package com.effectivem.android.data.api

import retrofit2.http.GET

interface LatestApi {
    @GET(
        "/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7"
    )
    suspend fun fetch(): LatestResponse
}