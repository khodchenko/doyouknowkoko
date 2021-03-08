package com.example.doyouknowkoko

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

private const val TAG = "AddOutfit"

class AddOutfit : AppCompatActivity() {
    private var name: EditText? = null
    private var brand: EditText? = null
    private var outfitSize: EditText? = null
    private var outfitComment: EditText? = null
    private var outfitPrice: EditText? = null
    private var btnAdd: Button? = null
    private var btnLoad: Button? = null
    private var dataBase: DatabaseReference? = null
    private val USER_KEY: String = "SHOES"
    private var counter: Int = 0
    private var id:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_outfit)
        name = findViewById(R.id.et_outfitName)
        brand = findViewById(R.id.et_outfitBrand)
        outfitSize = findViewById(R.id.et_outfitSize)
        outfitComment = findViewById(R.id.et_outfitComment)
        outfitPrice = findViewById(R.id.et_outfitPrice)
        btnAdd = findViewById(R.id.btn_addOutfit)
        btnLoad = findViewById(R.id.btn_load)



        btnAdd?.setOnClickListener {
            onClickSave()
            Toast.makeText(this, "Добавлено в базу", Toast.LENGTH_SHORT).show()
        }
        btnLoad?.setOnClickListener {
            onClickRead()
        }
    }

    private fun onClickSave() {
        id++
        dataBase = FirebaseDatabase.getInstance().getReference(id.toString())
        var outfitNameSave = name?.text.toString()
        var outfitBrandSave = brand?.text.toString()
        var outfitSizeSave = outfitSize?.text.toString()
        //var outfitCommentSave = outfitComment?.text.toString()
        //var outfitPriceSave = outfitPrice?.text.toString()

        dataBase?.setValue(Outfit(outfitNameSave, outfitBrandSave, outfitSizeSave))
    }

    fun onClickRead() {

        var getData = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    var ab = StringBuilder()
                for (i in snapshot.children){
                    var name = i.child("name").value
                    val brand = i.child("brand").getValue()
                    val size = i.child("size").getValue()
                    //val outfitComment = i.child("outfitComment").getValue()
                    //val outfitPrice = i.child("outfitPrice").getValue()
                    ab.append("${i.key} $name $brand $size")

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