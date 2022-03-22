package com.example.sqlitesampleproject

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_add_record.*
import java.util.jar.Manifest

class AddRecordActivity : AppCompatActivity() {

    //permission constants
    private val PICK_PHOTO_CODE = 100


    private var imageUri: Uri? = null
    private var name:String? = ""
    private var phone:String? = ""
    private var email:String? = ""
    private var dob:String? = ""
    private var bio:String? = ""

    private var actionBar: ActionBar? = null
    lateinit var dbHelper: MyDbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        actionBar = supportActionBar
        actionBar!!.title = "Add Record"
        //back button in action bar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        dbHelper = MyDbHelper(this)



        //click image view to pick image
        profileIV.setOnClickListener{
            val imagePickerIntent = Intent(Intent.ACTION_GET_CONTENT)
            imagePickerIntent.type = "image/*"
            if (imagePickerIntent.resolveActivity(packageManager) != null){
                startActivityForResult(imagePickerIntent,PICK_PHOTO_CODE)
            }

        }

        saveBtn.setOnClickListener{
            inPutData()
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PHOTO_CODE){
            if (resultCode == Activity.RESULT_OK){
                val resultUri = data?.data
                imageUri = resultUri
                profileIV.setImageURI(imageUri)



            }else{
                Toast.makeText(this, "Image Pick Action Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inPutData() {
        name = ""+nameET.text.toString().trim()
        phone = ""+PhoneET.text.toString().trim()
        email = ""+emailET.text.toString().trim()
        dob = ""+dobET.text.toString().trim()
        bio = ""+descriptioinET.text.toString().trim()

        val timeStamp = System.currentTimeMillis()

        val id = dbHelper.insertRecord(
            ""+name,
            ""+imageUri,
            ""+bio,
            ""+phone,
            ""+email,
            ""+dob,
            ""+timeStamp,
            ""+timeStamp
        
        )
        Toast.makeText(this, "Record added ID $id", Toast.LENGTH_SHORT).show()



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() //goback to previous activity
        return super.onSupportNavigateUp()
    }





}