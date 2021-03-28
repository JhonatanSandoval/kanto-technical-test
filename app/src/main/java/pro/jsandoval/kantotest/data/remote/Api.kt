package pro.jsandoval.kantotest.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("v3/{userUuid}")
    suspend fun getUserDetails(@Path("userUuid") id: String = "4efa83dd-6ff7-4bc1-9c17-3a45016978a7"): UserDetailsResponse

    @GET("v3/{dashboardUuid}")
    suspend fun getRecordList(@Path("dashboardUuid") id: String = "2f188654-7f58-4267-8887-ede536d8382e"): List<RecordResponse>

}