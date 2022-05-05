package apps.sai.com.animationactivity.score

import apps.sai.com.animationactivity.score.data.ScoreResponseModel
import apps.sai.com.animationactivity.score.data.IScoreApi
import apps.sai.com.animationactivity.score.data.IScoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ScoreRepositoryImpl(private val apiI: IScoreApi) : IScoreRepository {
    override suspend fun getScore(): Flow<ScoreResponseModel> {
        return flow { emit(apiI.getScore()) }
    }
}