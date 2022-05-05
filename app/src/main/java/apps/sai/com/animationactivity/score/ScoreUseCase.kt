package apps.sai.com.animationactivity.score

import apps.sai.com.animationactivity.score.data.ScoreResponseModel
import apps.sai.com.animationactivity.score.data.IScoreRepository
import kotlinx.coroutines.flow.Flow

class ScoreUseCase(private val repository: IScoreRepository) {
    suspend fun getScore(): Flow<ScoreResponseModel>{
        return repository.getScore()
    }
}