package apps.sai.com.animationactivity.di

import apps.sai.com.animationactivity.score.ScoreRepositoryImpl
import apps.sai.com.animationactivity.score.ScoreUseCase
import apps.sai.com.animationactivity.score.data.IScoreApi
import apps.sai.com.animationactivity.score.data.IScoreRepository

/**
 * Class for feature dependencies
 */
object RepositoryModule {
    fun provideScoreRepository(api: IScoreApi): IScoreRepository =
        ScoreRepositoryImpl(api)

    fun provideScoreUseCase(repository: IScoreRepository) =
        ScoreUseCase(repository)
}

