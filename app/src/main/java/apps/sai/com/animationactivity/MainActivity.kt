package apps.sai.com.animationactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import apps.sai.com.animationactivity.score.ScoreFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ScoreFragment.newInstance())
                .commitNow()
        }
    }
}