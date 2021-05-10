package com.example.telegram.ui.fragments

import androidx.fragment.app.Fragment
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.activities.RegisterActivity
import com.example.telegram.utilits.AUTH
import com.example.telegram.utilits.AppTextWatcher
import com.example.telegram.utilits.replaceActivity
import com.example.telegram.utilits.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(val phoneNumber: String, val id: String) : Fragment(R.layout.fragment_enter_code) {

    private lateinit var mAuth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = phoneNumber
        register_input_code.run {
            register_input_code.addTextChangedListener(AppTextWatcher {

                val string = register_input_code.text.toString()
                if (string.length == 6) {
                    enterCode()
                }
            })
        }
    }

    private fun enterCode() {
        val code =register_input_code.text.toString()
        val credentional =PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credentional).addOnCompleteListener {
            if (it.isSuccessful){
                showToast("Добро пожаловать")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else showToast(it.exception?.message.toString())

        }

    }
}