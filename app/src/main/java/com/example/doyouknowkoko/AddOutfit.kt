package com.example.doyouknowkoko

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private const val TAG = "AddOutfit"

class AddOutfit : AppCompatActivity() {
    private var outfitName: EditText? = null
    private var outfitBrand: EditText? = null
    private var outfitSize: EditText? = null
    private var outfitComment: EditText? = null
    private var outfitPrice: EditText? = null
    private var btnAdd: Button? = null
    private var btnLoad: Button? = null
    private var dataBase: DatabaseReference? = null
    private val USER_KEY: String = "Outfit"
    private var id:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_outfit)
        outfitName = findViewById(R.id.et_outfitName)
        outfitBrand = findViewById(R.id.et_outfitBrand)
        outfitSize = findViewById(R.id.et_outfitSize)
        outfitComment = findViewById(R.id.et_outfitComment)
        outfitPrice = findViewById(R.id.et_outfitPrice)
        btnAdd = findViewById(R.id.btn_addOutfit)
        btnLoad = findViewById(R.id.btn_load)
        dataBase = FirebaseDatabase.getInstance().reference


        btnAdd?.setOnClickListener {
            onClickSave()
            Toast.makeText(this, "Добавлено в базу", Toast.LENGTH_SHORT).show()
        }
        btnLoad?.setOnClickListener {
            onClickRead()
        }
    }

    private fun onClickSave() {
//        val typeSave = dataBase?.key
////        var idSave = id++
////        var outfitNameSave = outfitName?.text.toString()
////        var outfitBrandSave = outfitBrand?.text.toString()
////        var outfitSizeSave = outfitSize?.text.toString()
////        var outfitCommentSave = outfitComment?.text.toString()
////        var outfitPriceSave = outfitPrice?.text.toString()
////
////        val newOutfit = Outfit(
////                typeSave,
////                idSave,
////                outfitNameSave,
////                outfitBrandSave,
////                outfitSizeSave,
////                outfitCommentSave,
////                outfitPriceSave
////        )
////        dataBase?.push()?.setValue(newOutfit)
        var outfitNameSave = outfitName?.text.toString()
        var outfitBrandSave = outfitBrand?.text.toString()
        var outfitSizeSave = outfitSize?.text.toString()
        var outfitCommentSave = outfitComment?.text.toString()
        var outfitPriceSave = outfitPrice?.text.toString()

        dataBase?.child(id.toString())?.setValue(Outfit(outfitNameSave,outfitBrandSave,outfitSizeSave,outfitCommentSave,outfitPriceSave))
    }

    fun onClickRead() {
        val intent = Intent(this, ReadActivity::class.java)
        startActivity(intent)


    }
}