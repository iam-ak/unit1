package com.example.ca2.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ca2.R
import com.example.ca2.custom.CustomProgressView
import java.util.Calendar

class ReceiverFragment : Fragment() {

    private lateinit var textViewReceivedData: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewTime: TextView
    private lateinit var btnPickDate: Button
    private lateinit var btnPickTime: Button
    private lateinit var customProgressView: CustomProgressView

    private var selectedDay = 0
    private var selectedMonth = 0
    private var selectedYear = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.receiver_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewReceivedData = view.findViewById(R.id.textViewReceivedData)
        textViewDate = view.findViewById(R.id.textViewDate)
        textViewTime = view.findViewById(R.id.textViewTime)
        btnPickDate = view.findViewById(R.id.btnPickDate)
        btnPickTime = view.findViewById(R.id.btnPickTime)
        customProgressView = view.findViewById(R.id.customProgressView)

        btnPickDate.setOnClickListener {
            showDatePicker()
        }

        btnPickTime.setOnClickListener {
            showTimePicker()
        }

        // Initialize progress view
        customProgressView.setProgressText("65%")
        customProgressView.setProgress(65f)
    }

    fun receiveData(message: String) {
        textViewReceivedData.text = "Received: $message"
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                this.selectedYear = selectedYear
                this.selectedMonth = selectedMonth + 1
                this.selectedDay = selectedDay
                textViewDate.text = "Date: $selectedDay/$selectedMonth/$selectedYear"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, selectedHour, selectedMinute ->
                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                textViewTime.text = "Time: $formattedTime"
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }
}
