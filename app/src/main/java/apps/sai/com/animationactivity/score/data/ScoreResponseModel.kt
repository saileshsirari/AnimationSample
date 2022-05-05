package apps.sai.com.animationactivity.score.data

import com.google.gson.annotations.SerializedName

data class ScoreResponseModel(
    @SerializedName("score") val score: Int,
)