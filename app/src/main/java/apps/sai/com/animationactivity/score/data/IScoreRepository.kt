package apps.sai.com.animationactivity.score.data

import kotlinx.coroutines.flow.Flow

interface IScoreRepository {
    suspend fun getScore(): Flow<ScoreResponseModel>
}