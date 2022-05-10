package com.android.calculator.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.calculator.R
import com.android.calculator.databinding.ActivityMainBinding
import com.android.calculator.utility.TinyDB
import com.android.calculator.utility.utility.givetimestamp
import com.android.calculator.utility.utility.randomString
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import org.mariuszgromada.math.mxparser.Expression


private lateinit var binding: ActivityMainBinding
var workingsTV: TextView? = null
var resultsTV: TextView? = null

var eqnarray="";
var sumarray="";
private lateinit var db: FirebaseFirestore
private lateinit var tinyDB: TinyDB;
var workings = ""

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)
        workingsTV = binding.workingsTextView
        resultsTV = binding.resultTextView
        tinyDB = TinyDB();
        db = FirebaseFirestore.getInstance()
    }




    @SuppressLint("NewApi")
    private fun setWorkings(givenValue: String) {
        workings = workings + givenValue
        workingsTV!!.text = workings
        showResult()
    }


    fun clear(view: View?) {
        workingsTV!!.text = ""
        workings = ""
        resultsTV!!.text = ""
    }


    fun division(view: View?) {
        setWorkings("/")
    }

    fun multi(view: View?) {
        setWorkings("*")
    }

    fun plus(view: View?) {
        setWorkings("+")
    }

    fun minus(view: View?) {
        setWorkings("-")
    }

    fun decimal(view: View?) {
        setWorkings(".")
    }





    fun zero(view: View?) {
        setWorkings("0")

    }

    fun one(view: View?) {
        setWorkings("1")
    }

    fun two(view: View?) {
        setWorkings("2")
    }

    fun three(view: View?) {
        setWorkings("3")
    }
    fun four(view: View?) {
        setWorkings("4")
    }
    fun five(view: View?) {
        setWorkings("5")
    }
    fun six(view: View?) {
        setWorkings("6")
    }
    fun seven(view: View?) {
        setWorkings("7")
    }

    fun eight(view: View?) {
        setWorkings("8")
    }

    fun nine(view: View?) {
        setWorkings("9")
    }




    @RequiresApi(Build.VERSION_CODES.N)
    fun equals(view: View?) {
        eqnarray=workingsTV!!.text.toString()
        sumarray=resultsTV!!.text.toString()

            if(eqnarray.length > 0 && sumarray.length>0)
            {
                storedata(eqnarray, sumarray);
            }
        workingsTV?.setText("")
        workingsTV?.text =resultsTV!!.text.toString()
        resultsTV?.setText("")


    }

    private fun getInputExpression(): String {
        var expression = binding.workingsTextView.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
//                resultsTV!!.text = "Error"
//                resultsTV!!.setTextColor(ContextCompat.getColor(this, R.color.red))
            } else {
                // Show Result
               resultsTV!!.text = DecimalFormat("0.######").format(result).toString()
                resultsTV!!.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))






            }
        } catch (e: Exception) {
            // Show Error Message
//            resultsTV!!.text = "Error"
//            resultsTV!!.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }

    fun history(view: View) {
        var intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    fun storedata(eqnarray:String,sumarray:String){
        val offerdata = hashMapOf(
            "all_eqnarray" to eqnarray,
            "all_sumarray" to sumarray,
            "added_timestamp" to givetimestamp()
            )

        db.collection("results")
            .document("caldetails_"+randomString(5))
            .set(
                offerdata,
                SetOptions.merge()
            ).addOnSuccessListener{

            }
            .addOnFailureListener{

            }
    }
}