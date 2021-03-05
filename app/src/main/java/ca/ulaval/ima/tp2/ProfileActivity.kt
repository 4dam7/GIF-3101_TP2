package ca.ulaval.ima.tp2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat

class ProfileActivity : AppCompatActivity() {
    val dateFormatter = SimpleDateFormat("yyyy-dd-MM")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);

        var profile = intent.getParcelableExtra<Profile>("PROFILE_OBJECT");
        if (profile != null) {
            this.findViewById<TextView>(R.id.firstname_placeholder).setText(profile?.firstName)
            this.findViewById<TextView>(R.id.lastname_placeholder).setText(profile?.lastName)
            this.findViewById<TextView>(R.id.birthdate_placeholder).setText(dateFormatter.format(profile?.birthDate?.time))
            this.findViewById<TextView>(R.id.gender_placeholder).setText(if (profile?.sexe == "opt_male") "Masculin" else "Féminin")
            this.findViewById<TextView>(R.id.program_placeholder).setText(profile?.program)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }
}