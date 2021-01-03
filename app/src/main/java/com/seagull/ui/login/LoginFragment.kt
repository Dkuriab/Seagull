package com.seagull.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.seagull.R
import java.util.regex.Pattern

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    val EMAIL_REG_EXP = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = view.findViewById<EditText>(R.id.email)
        val emailWrapper = view.findViewById<TextInputLayout>(R.id.email_wrapper)
        val passwordWrapper = view.findViewById<TextInputLayout>(R.id.password_wrapper)
        val password = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            //TODO MVVM
            if (!validateEmail(username.text.toString())) {
                emailWrapper.error = "Invalid Email"
            } else if (!validatePassword(password.text.toString())){
                passwordWrapper.error = "InvalidPassword"
                emailWrapper.isErrorEnabled = false
            } else {
                passwordWrapper.isErrorEnabled = false
                emailWrapper.isErrorEnabled = false

                //TODO go to server with callback
                activity?.finish()
            }
        }
    }

    private fun validateEmail(email: String) : Boolean {
        return Pattern.compile(EMAIL_REG_EXP).matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.length > 5
    }
}