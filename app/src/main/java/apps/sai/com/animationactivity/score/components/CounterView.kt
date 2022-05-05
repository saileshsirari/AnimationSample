package apps.sai.com.animationactivity.score.components

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import apps.sai.com.animationactivity.R

/**
 * Simple custom counter view
 */
class CounterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        val attributes: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CounterView
        )
        attributes.recycle()
    }
}