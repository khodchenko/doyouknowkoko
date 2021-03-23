package com.example.doyouknowkoko

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.util.*


private const val TAG = "AddOutfit"

class AddOutfit : AppCompatActivity() {
    private var nameView: EditText? = null
    private var brandView: EditText? = null
    private var sizeView: EditText? = null
    private var commentView: EditText? = null
    private var priceView: EditText? = null
    private var btnAdd: Button? = null
    private var btnLoad: Button? = null
    private var dataBase: DatabaseReference? = null


    private lateinit var outfitImage: ImageView
    private var selectedPhotoUri: Uri? = null

    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_outfit)

        nameView = findViewById(R.id.et_outfitName)
        brandView = findViewById(R.id.et_outfitBrand)
        sizeView = findViewById(R.id.et_outfitSize)
        commentView = findViewById(R.id.et_outfitComment)
        priceView = findViewById(R.id.et_outfitPrice)

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
        outfitImage.setOnClickListener {
            chooseImage()
        }


    }
    //SAVE BUTTON
    private fun onClickSave() {

        uploadImageToFirebase()
    }

    //TEST BUTTON
    private fun onClickRead() {
        uploadImageToFirebase()

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
            selectedPhotoUri = data.data
            outfitImage.setImageURI(selectedPhotoUri)
        }
    }

    private fun uploadImageToFirebase() {
        val pd: ProgressDialog = ProgressDialog(this)
        pd.setTitle("Uploading image...")
        pd.show()
        val fileName = UUID.randomUUID().toString()
        val refStorage = FirebaseStorage.getInstance().getReference("/images/$fileName")

        refStorage.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                    pd.dismiss() //close loading
                    refStorage.downloadUrl.addOnSuccessListener {

                        saveDataToDatabase(it.toString()) //get downloading url
                    }
                }
            .addOnFailureListener { e ->
                print(e.message)
                pd.dismiss()
            }
            //progressbar
            .addOnProgressListener {
                var progressPercents: Double = (100.00 * it.bytesTransferred / it.totalByteCount)
                pd.setMessage("Percentage: $progressPercents%")
            }
    }

    private fun saveDataToDatabase(imageUrl:String) {
        dataBase = FirebaseDatabase.getInstance().getReference(UUID.randomUUID().toString())

        dataBase?.setValue(
            Outfit(
                nameView?.text.toString(),
                brandView?.text.toString(),
                sizeView?.text.toString(),
                commentView?.text.toString(),
                priceView?.text.toString(),
                imageUrl
            )
        )
    }
}