package com.example.doyouknowkoko

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

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

        var outfitNameSave = outfitName?.text.toString()
        var outfitBrandSave = outfitBrand?.text.toString()
        var outfitSizeSave = outfitSize?.text.toString()
        var outfitCommentSave = outfitComment?.text.toString()
        var outfitPriceSave = outfitPrice?.text.toString()

        dataBase?.child(id.toString())?.setValue(Outfit(outfitNameSave, outfitBrandSave, outfitSizeSave, outfitCommentSave, outfitPriceSave))
    }

    fun onClickRead() {

        var getData = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    var ab = StringBuilder()
                for (i in snapshot.children){
                    var outfitName = i.child("outfitName").getValue()
                    val outfitBrand = i.child("outfitBrand").getValue()
                    val outfitSize = i.child("outfitSize").getValue()
                    val outfitComment = i.child("outfitComment").getValue()
                    val outfitPrice = i.child("outfitPrice").getValue()
                    ab.append("${i.key} $outfitName $outfitBrand $outfitSize $outfitComment $outfitPrice")

                }
                Toast.makeText(applicationContext, ab, Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }
        dataBase?.addValueEventListener(getData)
        dataBase?.addListenerForSingleValueEvent(getData)

    }
}