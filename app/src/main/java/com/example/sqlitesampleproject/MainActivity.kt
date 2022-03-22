package com.example.sqlitesampleproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

//should add permissions: WRITE_EXTERNAL-STORAGE, CAMERA
class MainActivity : AppCompatActivity() {
    lateinit var dbHelper: MyDbHelper
    private val NEWEST_FIRST = "${Constants.C_ADDED_TIME_STAMP} DESC"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = MyDbHelper(this)
        loadRecords()

        addRecordsBtn.setOnClickListener{
            startActivity(Intent(this,AddRecordActivity::class.java))
        }


    }

    private fun loadRecords() {
        homeRecycler.layoutManager = LinearLayoutManager(this)
        val adapterRecord = HomeAdapter(this, dbHelper.getAllData(NEWEST_FIRST))
        homeRecycler.adapter = adapterRecord

    }

    override fun onResume() {
        super.onResume()
        loadRecords()
    }
}