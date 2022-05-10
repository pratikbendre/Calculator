package com.android.calculator.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.calculator.adapter.cal_history
import com.android.calculator.databinding.ActivityHistoryBinding
import com.android.calculator.databinding.ActivityMainBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

private lateinit var binding: ActivityHistoryBinding
private lateinit var db: FirebaseFirestore
var callist = ArrayList<DocumentSnapshot>()
var caldocmentlist = ArrayList<DocumentSnapshot>()
lateinit var calculationadapter: cal_history;
private var loadingbox: ProgressDialog? = null

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        loadingbox = ProgressDialog(this)
        loadingbox!!.setMessage("Please Wait....")
        loadingbox!!.setCancelable(false)


        db = FirebaseFirestore.getInstance()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,
            false
        )

        calculationadapter=cal_history(applicationContext,caldocmentlist)
        binding.callistview.adapter = calculationadapter
        binding.callistview.layoutManager = layoutManager
        readDataFromFirestore()

    }

    private fun readDataFromFirestore() {
        callist.clear()
        caldocmentlist.clear()
        calculationadapter.notifyDataSetChanged()
        loadingbox?.show()
        db.collection("results")
            .orderBy("added_timestamp",Query.Direction.ASCENDING)
            .limit(10)
            .get()
            .addOnSuccessListener { documents ->

                if (documents.size() > 0) {
                    for (document in documents) {
                        Log.d("c123",document.toString());
                        callist.add(document)
                        caldocmentlist.add(document)

                    }

                    loadingbox?.dismiss()
                    calculationadapter.notifyDataSetChanged()
                }
                loadingbox?.dismiss()
                calculationadapter.notifyDataSetChanged()

            }
            .addOnFailureListener {
                Log.d("c123","Error");
            }

    }

}