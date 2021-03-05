package ca.ulaval.ima.tp2.ui.infos

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.Profile
import ca.ulaval.ima.tp2.ProfileActivity
import ca.ulaval.ima.tp2.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*


class FormFragment : Fragment() {

    val dateFormatter = SimpleDateFormat("yyyy-dd-MM")
    lateinit var currentBirthDate: Calendar;

    fun setSelectOptions(viewCtx: View) {
        val arrayList: ArrayList<String> = ArrayList()
        arrayList.add("GEL")
        arrayList.add("GIF")
        arrayList.add("GLO")
        arrayList.add("IFT")
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, arrayList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewCtx.findViewById<Spinner>(R.id.spinner).adapter = arrayAdapter
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val viewCtx = inflater.inflate(R.layout.fragment_form, container, false)
        val bundle: Bundle? = this.arguments

        val datePicker = viewCtx.findViewById<TextInputEditText>(R.id.dateInput)

        setSelectOptions(viewCtx)

        if (bundle != null) {
            var profile = bundle.getParcelable<Profile>("PROFILE")
            currentBirthDate = profile?.birthDate!!

            viewCtx.findViewById<TextInputEditText>(R.id.text_field_first_name_input).setText(profile?.firstName)
            viewCtx.findViewById<TextInputEditText>(R.id.text_field_last_name_input).setText(profile?.lastName)
            viewCtx.findViewById<TextInputEditText>(R.id.dateInput).setText(dateFormatter.format(profile?.birthDate?.time))
            viewCtx.findViewById<RadioGroup>(R.id.radioGroup).check(if (profile?.sexe == "opt_male") R.id.opt_male else R.id.opt_female)

            datePicker.setOnClickListener {
                activity?.let { it1 ->
                    val formatter = SimpleDateFormat("yyyy-dd-MM")
                    val birthDate = Calendar.getInstance()
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        birthDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        birthDate.set(Calendar.MONTH, monthOfYear)
                        birthDate.set(Calendar.YEAR, year)
                        datePicker.setText(formatter.format(currentBirthDate.time))
                        currentBirthDate = birthDate
                    },
                            profile?.birthDate?.get(Calendar.YEAR)!!,
                            profile.birthDate?.get(Calendar.MONTH)!!,
                            profile.birthDate?.get(Calendar.DAY_OF_MONTH)!!)
                }?.show()
            }

            viewCtx.findViewById<Button>(R.id.submit_button).setOnClickListener {
                onSubmit(viewCtx, profile)
            }
        }

        return viewCtx
    }

    fun onSubmit(viewCtx: View, profile: Profile) {
        profile.birthDate = currentBirthDate
        profile.firstName = viewCtx.findViewById<TextInputEditText>(R.id.text_field_first_name_input).text.toString()
        profile.lastName =  viewCtx.findViewById<TextInputEditText>(R.id.text_field_last_name_input).text.toString()
        profile.program = viewCtx.findViewById<Spinner>(R.id.spinner).selectedItem.toString()
        profile.sexe = viewCtx.findViewById<RadioGroup>(R.id.radioGroup).checkedRadioButtonId.toString()
        profile.edited = true

        val intent = Intent(requireContext(), ProfileActivity::class.java)
        intent.putExtra("PROFILE_OBJECT", profile);
        startActivity(intent)
    }

}