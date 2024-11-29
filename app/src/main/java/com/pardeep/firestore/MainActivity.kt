package com.pardeep.firestore

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.pardeep.firestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , RecyclerInterface {
    var binding : ActivityMainBinding?=null
    lateinit var linearLayoutManager: LinearLayoutManager
    var itemArray = arrayListOf<ItemData>()
    var myAdapter = MyAdapter(itemArray,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding?.RecyclerView?.adapter = myAdapter
        linearLayoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL,false)
        binding?.RecyclerView?.layoutManager = linearLayoutManager
        binding?.fabBtn?.setOnClickListener{
            Dialog(this).apply {
                setContentView(R.layout.custom_dialog_view)
                window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
                val nameEt = findViewById<EditText>(R.id.nameEt)
                val rollEt = findViewById<EditText>(R.id.rollNoEt)
                val imageBtn = findViewById<Button>(R.id.imageSelectionBtn)
                val add = findViewById<Button>(R.id.Add)

                add.setOnClickListener {
                    if (nameEt.text.trim().isNullOrEmpty()){
                        nameEt.error = "enter name"
                    }else if (rollEt.text.trim().isNullOrEmpty()){
                        rollEt.error = "enter roll number"
                    }else{
                        itemArray.add(ItemData(nameEt.text.toString(),rollEt.text.toString().toInt()))
                        myAdapter.notifyDataSetChanged()
                        dismiss()
                    }
                }

            }.show()
        }
    }

    override fun delete(position: Int) {
        itemArray.removeAt(position)
        myAdapter.notifyDataSetChanged()
    }

    override fun update(position: Int) {
        Dialog(this).apply {
            setContentView(R.layout.custom_dialog_view)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            val nameEt = findViewById<EditText>(R.id.nameEt)
            val rollEt = findViewById<EditText>(R.id.rollNoEt)
            val imageBtn = findViewById<Button>(R.id.imageSelectionBtn)
            val add = findViewById<Button>(R.id.Add)
            nameEt.setText(itemArray[position].name)
            rollEt.setText(itemArray[position].rollNo.toString())
            add.setText("Update")
            add.setOnClickListener {

                if (nameEt.text.trim().isNullOrEmpty()){
                    nameEt.error = "enter name"
                }else if (rollEt.text.trim().isNullOrEmpty()){
                    rollEt.error = "enter roll number"
                }else{
                    itemArray.set(position,ItemData(nameEt.text.toString(),rollEt.text.toString().toInt()))
                    myAdapter.notifyDataSetChanged()
                    dismiss()
                }}

            }.show()

    }
}