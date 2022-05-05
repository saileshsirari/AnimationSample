package apps.sai.com.animationactivity.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import apps.sai.com.animationactivity.R
import apps.sai.com.animationactivity.databinding.FragmentScoreBinding
import kotlinx.coroutines.launch


class ScoreFragment : Fragment() {

    private var bounceAnimation: Animation? = null
    private lateinit var viewModel: ScoreViewModel
    private var arrayList: ArrayList<Int> = ArrayList()
    private var progress = 0
    private lateinit var viewBinding: FragmentScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ScoreViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentScoreBinding>(
            inflater,
            R.layout.fragment_score,
            container,
            false
        ).apply {
            viewBinding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bounceAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.bounce_animation)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.getScore().collect { score ->
                    val formatted: String = resources.getString(
                        R.string.general_button_timer,
                        score
                    )
                    viewModel.currentScore = score
                    viewBinding.tvTimer.text = formatted
                    //for showing progress
                    if (score.rem(viewModel.progressDivisions) == 0) {
                        viewBinding.spLevels.refresh()
                    }
                }
            }
        }
        viewModel.scoreLiveData.observe(viewLifecycleOwner){
            viewBinding.tvTimer.startAnimation(bounceAnimation)
            viewBinding.tvTimer.visibility = View.VISIBLE
        }
        viewModel.levelStartedLiveData.observe(viewLifecycleOwner) {
            viewBinding.spLevels.visibility = View.VISIBLE
            viewBinding.spLevels.setEnabledDivisions(arrayList)
            viewBinding.spLevels.setDivisions(viewModel.divisions)
            viewBinding.spLevels.segmentDivisions = viewModel.progressDivisions
        }

        viewModel.levelAchievedLiveData.observe(viewLifecycleOwner) {
            if (it) {
                arrayList.add(progress)
                progress += 1;
                viewBinding.spLevels.setEnabledDivisions(arrayList)
                viewBinding.tvTitle.visibility = View.VISIBLE
                viewBinding.lvExplosion.playAnimation()
            } else {
                viewBinding.tvTitle.visibility = View.INVISIBLE
            }
        }
        viewModel.levelEndedLiveData.observe(viewLifecycleOwner) {
            viewBinding.abCert.visibility =View.VISIBLE
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),getString(R.string.error_text),Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance() = ScoreFragment()
    }
}