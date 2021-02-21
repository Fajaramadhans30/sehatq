package com.test.sehatqtest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import studios.codelight.smartloginlibrary.*
import studios.codelight.smartloginlibrary.users.SmartFacebookUser
import studios.codelight.smartloginlibrary.users.SmartGoogleUser
import studios.codelight.smartloginlibrary.users.SmartUser
import studios.codelight.smartloginlibrary.util.SmartLoginException

class LoginActivity : AppCompatActivity(), SmartLoginCallbacks {
    lateinit var facebookLoginButton: Button
    lateinit var googleLoginButton: Button
    lateinit var customSigninButton: Button
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    var currentUser: SmartUser? = null

    //GoogleApiClient mGoogleApiClient;
    var config: SmartLoginConfig? = null
    var smartLogin: SmartLogin? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindViews()
        setListeners()
        config = SmartLoginConfig(this, this)
        config!!.facebookAppId = getString(R.string.facebook_app_id)
        config!!.facebookPermissions = null
        config!!.googleApiClient = null
    }

    override fun onResume() {
        super.onResume()
        currentUser = UserSessionManager.getCurrentUser(this)
        refreshLayout()
    }

    private fun refreshLayout() {
        currentUser = UserSessionManager.getCurrentUser(this)
        if (currentUser != null) {
            val intentLogin = Intent(this, MainActivity::class.java)
            startActivity(intentLogin)
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (smartLogin != null) {
            smartLogin!!.onActivityResult(requestCode, resultCode, data, config)
        }
    }

    private fun setListeners() {
        facebookLoginButton!!.setOnClickListener { // Perform Facebook login
            smartLogin = SmartLoginFactory.build(LoginType.Facebook)
            smartLogin!!.login(config)
        }
        googleLoginButton!!.setOnClickListener { // Perform Google login
            smartLogin = SmartLoginFactory.build(LoginType.Google)
            smartLogin!!.login(config)
        }
        customSigninButton!!.setOnClickListener { // Perform custom sign in
            smartLogin = SmartLoginFactory.build(LoginType.CustomLogin)
            smartLogin!!.login(config)
        }
    }

    private fun bindViews() {
        facebookLoginButton =
            findViewById<View>(R.id.facebook_login_button) as Button
        googleLoginButton =
            findViewById<View>(R.id.google_login_button) as Button
        customSigninButton =
            findViewById<View>(R.id.custom_signin_button) as Button
        emailEditText = findViewById<View>(R.id.email_edittext) as EditText
        passwordEditText = findViewById<View>(R.id.password_edittext) as EditText
    }

    override fun onLoginSuccess(user: SmartUser) {
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show()
        refreshLayout()
    }

    override fun onLoginFailure(e: SmartLoginException) {
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
    }

    override fun doCustomLogin(): SmartUser {
        val user = SmartUser()
        user.email = emailEditText!!.text.toString()
        return user
    }

    override fun doCustomSignup(): SmartUser {
        val user = SmartUser()
        user.email = emailEditText!!.text.toString()
        return user
    }
}