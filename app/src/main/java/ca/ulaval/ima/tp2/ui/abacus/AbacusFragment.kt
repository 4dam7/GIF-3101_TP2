package ca.ulaval.ima.tp2.ui.abacus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.R
import com.google.android.material.slider.Slider

class AbacusFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_abacus, container, false)

        root.findViewById<Slider>(R.id.abacus_first_slider).addOnChangeListener { slider, value, fromUser ->
            onChangeFirstSlider(root, value)
        }

        root.findViewById<Slider>(R.id.abacus_second_slider).addOnChangeListener { slider, value, fromUser ->
            onChangeSecondSlider(root, value)
        }

        return root
    }

    fun setResultSlider(viewCtx: View, value: Float) {
        viewCtx.findViewById<Slider>(R.id.abacus_result_slider).value = value
        viewCtx.findViewById<TextView>(R.id.abacus_result_slider_value_label_placeholder).text = value.toInt().toString()
    }


    fun onChangeFirstSlider(viewCtx: View, value: Float) {
        val secondSliderValue = viewCtx.findViewById<Slider>(R.id.abacus_second_slider).value

        viewCtx.findViewById<TextView>(R.id.abacus_first_slider_value_label_placeholder).text = value.toInt().toString()
        setResultSlider(viewCtx, value*secondSliderValue)
    }

    fun onChangeSecondSlider(viewCtx: View, value: Float) {
        val firstSliderValue = viewCtx.findViewById<Slider>(R.id.abacus_first_slider).value

        viewCtx.findViewById<TextView>(R.id.abacus_second_slider_value_label_placeholder).text = value.toInt().toString()
        setResultSlider(viewCtx, value*firstSliderValue)
    }
}