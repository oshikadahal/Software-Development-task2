package com.example.mytask

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mytask.databinding.ActivityAssignmentBinding

class AssignmentActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityAssignmentBinding

    val countries = arrayOf("Nepal")

    val cities = arrayOf("Kathmandu", "Kirtipur", "Kanchanpur", "Dhading", "Bharatpur", "Bhaktapur", "Lalitpur", "Chitwan", "Biratnagar", "Pokhara")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAssignmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryAdapter = ArrayAdapter(this@AssignmentActivity, android.R.layout.simple_dropdown_item_1line, countries)

        countryAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        binding.countryDropDown.adapter = countryAdapter
        binding.countryDropDown.onItemSelectedListener = this

        val cityAdapter = ArrayAdapter(this@AssignmentActivity, android.R.layout.simple_dropdown_item_1line, cities)
        binding.chooseCities.setAdapter(cityAdapter)
        binding.chooseCities.threshold = 2

        binding.submitButton.setOnClickListener{
            val fullName : String = binding.fullName.text.toString()
            val email : String = binding.email.text.toString()
            val password : String = binding.password.text.toString()
            val gender: String = if (binding.radioMale.isChecked){
                "Male"
            } else if (binding.femaleRadio.isChecked){
                "Female"
            }else{
                ""
            }
            val country = binding.countryDropDown.selectedItem.toString()
            val city = binding.chooseCities.text.toString()

            if (fullName.isEmpty()) {
                binding.fullName.error = "Name cannot be empty"
            } else if (email.isEmpty()) {
                binding.email.error = "Email cannot be empty"
            } else if (password.isEmpty()) {
                binding.password.error = "Password cannot be empty"
            } else if (gender.isEmpty()) {
                Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show()
            } else if (country == "Select Country") {
                binding.selectedCountry.error = "Country cannot be empty"
            } else if (city.isEmpty()) {
                binding.chooseCities.error = "City cannot be empty"
            } else if (!binding.checkBox.isChecked) {
                Toast.makeText(this, "Please agree to terms and conditions", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@AssignmentActivity, NextActivity::class.java)
                intent.putExtra("FullName", fullName)
                intent.putExtra("Email", email)
                intent.putExtra("Password", password)
                intent.putExtra("Gender", gender)
                intent.putExtra("Country", country)
                intent.putExtra("City", city)
                startActivity(intent)
            }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selectedCountry = countries[p2]

        val cityAdapter = ArrayAdapter(this@AssignmentActivity, android.R.layout.simple_dropdown_item_1line, cities)
        binding.chooseCities.setAdapter(cityAdapter)


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}