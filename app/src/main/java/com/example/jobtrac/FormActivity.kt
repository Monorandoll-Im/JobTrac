package com.example.jobtrac

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.jobtrac.viewmodel.JobViewModel
import com.example.jobtrac.model.SubmittedForm
import com.example.jobtrac.repo.FormRepository
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts




class FormActivity : AppCompatActivity() {
    private val viewModel: JobViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)


        val nameInput = findViewById<EditText>(R.id.inputName)
        val emailInput = findViewById<EditText>(R.id.inputEmail)
        val notesInput = findViewById<EditText>(R.id.inputNotes)
        val submitBtn = findViewById<Button>(R.id.submitButton)

        submitBtn.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val notes = notesInput.text.toString().trim()
            val form = SubmittedForm(name, email, notes)

            FormRepository.submittedForms.add(form)


            // You can save this info to ViewModel, API, or local database
            Toast.makeText(this, "Application submitted!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
