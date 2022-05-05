package apps.sai.com.animationactivity.di

import apps.sai.com.animationactivity.score.data.IScoreApi

object NetworkModule {

    fun provideScoreService(): IScoreApi {
        return IScoreApi.create()
    }
}
