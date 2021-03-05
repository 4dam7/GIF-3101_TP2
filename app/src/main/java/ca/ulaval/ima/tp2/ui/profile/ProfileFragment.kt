package ca.ulaval.ima.tp2.ui.infos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.Profile
import ca.ulaval.ima.tp2.R
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat

class ProfileFragment : Fragment() {
    val dateFormatter = SimpleDateFormat("yyyy-dd-MM")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val viewCtx = inflater.inflate(R.layout.activity_profile, container, false)
        val bundle: Bundle? = this.arguments

        if (bundle != null) {
            var profile = bundle.getParcelable<Profile>("PROFILE")

            if (profile?.edited == true) {
                viewCtx.findViewById<TextView>(R.id.firstname_placeholder).setText(profile?.firstName)
                viewCtx.findViewById<TextView>(R.id.lastname_placeholder).setText(profile?.lastName)
                viewCtx.findViewById<TextView>(R.id.birthdate_placeholder).setText(dateFormatter.format(profile?.birthDate?.time))
                viewCtx.findViewById<TextView>(R.id.gender_placeholder).setText(if (profile?.sexe == "opt_male") "Masculin" else "Féminin")
                viewCtx.findViewById<TextView>(R.id.program_placeholder).setText(profile?.program)
            }
        }


        return viewCtx
    }
}