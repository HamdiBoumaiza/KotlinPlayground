package com.hamdi.kotlinplayground

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_anko_commons.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar

class AnkoCommonActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        when (v.id) {
            R.id.toast -> showToast()
            R.id.snackbars -> showSnackBar()
            R.id.dialog -> showAlertDialog()
            R.id.browse -> browseAnkoWay()
            R.id.share -> shareAnkoWay()
            R.id.phoneCall -> makePhoneCall()
            R.id.phoneSms -> sendSMS()
            R.id.email -> sendEmail()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anko_commons)
        setClickListeners()
    }

    private fun setClickListeners() {
        dialog.setOnClickListener(this)
        snackbars.setOnClickListener(this)
        toast.setOnClickListener(this)
        browse.setOnClickListener(this)
        share.setOnClickListener(this)
        phoneCall.setOnClickListener(this)
        phoneSms.setOnClickListener(this)
        email.setOnClickListener(this)
    }

    private fun showToast() {
        val textExtra = intent.getStringExtra("FIRST_KEY")
        if (textExtra != null) toast("this is $textExtra")

        toast(R.string.anko_commons)
        longToast("Hello there , ".hastaLaVista())
    }

    private fun showSnackBar() {
        parentConstraintlayout.snackbar(" You just clicked me !".removeFirstAndLastChar()).show()
    }

    private fun showAlertDialog() {
        alert("Hi, I'm Roy", "Have you tried turning it off and on again?") {
            yesButton { toast("Oh…") }
            noButton { toast("NO") }
        }.show()
    }

    private fun browseAnkoWay() {
        browse("https://www.google.com/")

        //The other way
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://www.google.com/")
        startActivity(openURL)
    }

    private fun shareAnkoWay() {
        share("share this text", "Subject")
    }

    private fun makePhoneCall() {
        makeCall("22554488")
    }

    private fun sendSMS() {
        sendSMS("22554488", "text of the sms")
    }

    private fun sendEmail() {
        email("john@test.com", "subject of the email", "text of the email")
    }


}
