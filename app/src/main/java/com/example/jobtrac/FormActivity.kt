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
import kotlinx.coroutines.selects.select


class FormActivity : AppCompatActivity() {
    private val viewModel: JobViewModel by viewModels()

    private lateinit var selectFileButton: Button
    private lateinit var fileNameText: TextView
    private var selectedFileUri: Uri? = null

    private val filePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            selectedFileUri = uri
            fileNameText.text = "Selected: ${uri.lastPathSegment}"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)


        val nameInput = findViewById<EditText>(R.id.inputName)
        val emailInput = findViewById<EditText>(R.id.inputEmail)
        val notesInput = findViewById<EditText>(R.id.inputNotes)
        val submitBtn = findViewById<Button>(R.id.submitButton)

        selectFileButton = findViewById(R.id.selectFileButton)
        fileNameText = findViewById(R.id.fileNameText)

        selectFileButton.setOnClickListener{
            filePickerLauncher.launch("*/*")
        }

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
