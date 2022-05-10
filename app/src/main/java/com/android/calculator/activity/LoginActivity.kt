package com.android.calculator.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.calculator.databinding.ActivityLoginBinding
import com.android.calculator.utility.utility.maketoast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions


private lateinit var binding: ActivityLoginBinding
private lateinit var auth: FirebaseAuth
private var loadingbox: ProgressDialog? = null
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        auth = FirebaseAuth.getInstance()
        loadingbox = ProgressDialog(this)
        loadingbox!!.setMessage("Please Wait....")
        loadingbox!!.setCancelable(false)
        binding.login.setOnClickListener { validatedata() }

    }


    var useremailid = "";
    var userpassword = "";

    fun validatedata() {
        loadingbox?.show();
        useremailid = binding.userid.text.toString().toLowerCase().trim()
        userpassword = binding.userpassword.text.toString().toLowerCase().trim()
        if (useremailid.isEmpty() || useremailid == null) {
            maketoast(applicationContext, "Please Enter Email Id")
        } else if (userpassword.isEmpty() || userpassword == null) {
            maketoast(applicationContext, "Please Enter Password")
        } else {
            loginuser()
        }

    }
    fun loginuser() {

        auth.signInWithEmailAndPassword(useremailid,userpassword)
            .addOnSuccessListener {
                intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                loadingbox?.dismiss();
            }
            .addOnFailureListener {
                maketoast(baseContext,"You have Entered an Wrong Password")
                loadingbox?.dismiss();
            }

            }
    }

