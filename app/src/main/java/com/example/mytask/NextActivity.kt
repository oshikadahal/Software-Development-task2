package com.example.mytask

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mytask.databinding.ActivityAssignmentBinding
import com.example.mytask.databinding.ActivityNextBinding

class NextActivity : AppCompatActivity() {
    lateinit var binding: ActivityNextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNextBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fullName : String = intent.getStringExtra("FullName").toString()
        val email : String = intent.getStringExtra("Email").toString()
        val gender : String = intent.getStringExtra("Gender").toString()
        val country : String = intent.getStringExtra("Country").toString()
        val city : String = intent.getStringExtra("City").toString()

        binding.outputFullName.text = fullName
        binding.outputEmail.text = email
        binding.outputGender.text = gender
        binding.outputCountry.text = country
        binding.outputCity.text = city


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}