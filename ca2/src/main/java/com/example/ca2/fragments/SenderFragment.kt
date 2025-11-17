package com.example.ca2.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ca2.R

class SenderFragment : Fragment() {

    private lateinit var editTextMessage: EditText
    private lateinit var btnSendData: Button
    private var dataSenderListener: DataSenderListener? = null

    interface DataSenderListener {
        fun onDataSent(message: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sender_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextMessage = view.findViewById(R.id.editTextMessage)
        btnSendData = view.findViewById(R.id.btnSendData)

        btnSendData.setOnClickListener {
            val message = editTextMessage.text.toString()
            if (message.isNotEmpty()) {
                dataSenderListener?.onDataSent(message)
                Toast.makeText(context, "Data sent to Fragment B!", Toast.LENGTH_SHORT).show()
                editTextMessage.text.clear()
            } else {
                Toast.makeText(context, "Please enter a message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataSenderListener = context as? DataSenderListener
    }

    override fun onDetach() {
        super.onDetach()
        dataSenderListener = null
    }
}
