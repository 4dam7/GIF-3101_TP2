package ca.ulaval.ima.tp2.ui.internet

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ca.ulaval.ima.tp2.R


class InternetFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_internet, container, false)

        root.findViewById<ImageView>(R.id.indicator_drawable).setImageResource(R.drawable.circle_indicator_fail)

        root.findViewById<Button>(R.id.internet_status_button).setOnClickListener {
            onCheckStatusClicked(root)
        }
        return root
    }

    fun onCheckStatusClicked(viewCtx: View) {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ninfo = cm.activeNetworkInfo

        if (ninfo != null && ninfo.isConnected) {
            viewCtx.findViewById<TextView>(R.id.internet_status_text).text = ninfo.typeName
            viewCtx.findViewById<ImageView>(R.id.indicator_drawable).setImageResource(R.drawable.circle_indicator_success)
        } else {
            viewCtx.findViewById<TextView>(R.id.internet_status_text).text = "Aucune connexion"
            viewCtx.findViewById<ImageView>(R.id.indicator_drawable).setImageResource(R.drawable.circle_indicator_fail)
        }
    }
}