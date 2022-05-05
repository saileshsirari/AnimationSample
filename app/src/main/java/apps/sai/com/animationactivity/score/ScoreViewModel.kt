package apps.sai.com.animationactivity.score

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import apps.sai.com.animationactivity.di.NetworkModule
import apps.sai.com.animationactivity.di.RepositoryModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ScoreViewModel : ViewModel() {

    var divisions = 6 //default divisions
    var currentScore = 1
    val progressDivisions = 10
    val levelAchievedLiveData = MutableLiveData<Boolean>()
    val levelStartedLiveData = MutableLiveData<Boolean>()
    val scoreLiveData = MutableLiveData<Boolean>()

    val levelEndedLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Boolean>()
    private val scoreApi = NetworkModule.provideScoreService()
    private val scoreRepository = RepositoryModule.provideScoreRepository(scoreApi)
    private val scoreUseCase = RepositoryModule.provideScoreUseCase(scoreRepository)

    suspend fun getScore(): Flow<Int> = flow {
        try {
            scoreUseCase.getScore().collect { response ->
                val x = response.score
                var counter = currentScore
                if(currentScore==1) {
                    scoreLiveData.postValue(true)
                    divisions = if (x.rem(100) == 0) x.div(100) else x.div(100) + 1
                    delay(900)//for animation
                    levelStartedLiveData.postValue(true)
                }

                while (counter++ < x) {
                    if (counter.rem(100) == 0) {
                        levelAchievedLiveData.postValue(true)
                        delay(3000)
                        levelAchievedLiveData.postValue(false)
                    }
                    emit(counter + 1)
                    delay(15)
                }
                levelEndedLiveData.postValue(true)
                currentScore =1
            }
        } catch (e: Exception) {
            errorLiveData.postValue(true)
        }
    }
}