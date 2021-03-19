package com.example.doyouknowkoko

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.UploadTask
import java.util.*

private const val TAG = "AddOutfit"

class AddOutfit : AppCompatActivity() {
    private var name: EditText? = null
    private var brand: EditText? = null
    private var size: EditText? = null
    private var outfitComment: EditText? = null
    private var outfitPrice: EditText? = null
    private var btnAdd: Button? = null
    private var btnLoad: Button? = null
    private var dataBase: DatabaseReference? = null


    private lateinit var outfitImage: ImageView
    private var imageUri: Uri? = null

    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_outfit)

        name = findViewById(R.id.et_outfitName)
        brand = findViewById(R.id.et_outfitBrand)
        size = findViewById(R.id.et_outfitSize)
        outfitComment = findViewById(R.id.et_outfitComment)
        outfitPrice = findViewById(R.id.et_outfitPrice)
        btnAdd = findViewById(R.id.btn_addOutfit)
        btnLoad = findViewById(R.id.btn_load)


        btnAdd?.setOnClickListener {
            onClickSave()
            Toast.makeText(this, "Добавлено в базу", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        btnLoad?.setOnClickListener {
            onClickRead()
        }

        outfitImage = findViewById(R.id.iv_upload_image)
        outfitImage.setOnClickListener(View.OnClickListener() {
            chooseImage()
        })


    }
//SAVE BUTTON
    private fun onClickSave() {
        id++
        dataBase = FirebaseDatabase.getInstance().getReference(id.toString())
        var outfitNameSave = name?.text.toString()
        var outfitBrandSave = brand?.text.toString()
        var outfitSizeSave = size?.text.toString()
        var outfitCommentSave = outfitComment?.text.toString()
        var outfitPriceSave = outfitPrice?.text.toString()
        var outfitImageUrlSave = imageUri

        dataBase?.setValue(
            Outfit(
                outfitNameSave,
                outfitBrandSave,
                outfitSizeSave,
                outfitCommentSave,
                outfitPriceSave,
                outfitImageUrlSave.toString()
            )

        )
        uploadImageToFirebase(imageUri!!)
    }
//TEST BUTTON
    private fun onClickRead() {
        uploadImageToFirebase(imageUri!!)

    }

    //----------------UPLOAD IMAGE-----------------------
    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Please select..."), 1
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {

            // Get the Uri of data
            imageUri = data.data
            outfitImage.setImageURI(imageUri)
        }
    }

    private fun uploadImageToFirebase(fileUri: Uri) {

        val pd: ProgressDialog = ProgressDialog(this)
        pd.setTitle("Uploading image...")
        pd.show()

        val fileName = UUID.randomUUID().toString()
        val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

        refStorage.putFile(imageUri!!)
            .addOnSuccessListener(
                OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                        pd.dismiss()
                        Snackbar.make(findViewById(android.R.id.content), "Image uploaded.",Snackbar.LENGTH_LONG)
                       // val imageUrl = it.toString()

                    }
                })

            .addOnFailureListener(OnFailureListener { e ->
                print(e.message)
                pd.dismiss()
            })
                //progressbar
            .addOnProgressListener(OnProgressListener {
                var progressPercents:Double = (100.00 * it.bytesTransferred/it.totalByteCount)
                pd.setMessage("Percentage: $progressPercents%")
            })
    }
}