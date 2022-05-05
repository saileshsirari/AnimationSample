package apps.sai.com.animationactivity.score.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Used to connect to the Score API to fetch case studies
 */
interface IScoreApi {

    @GET("score.json")
    suspend fun getScore(
    ): ScoreResponseModel

    companion object {
        private const val BASE_URL =
            "http://englishscore.com/tech-test/"

        fun create(): IScoreApi {
            val logger = HttpLoggingInterceptor().apply { level = Level.BODY }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IScoreApi::class.java)
        }
    }
}
