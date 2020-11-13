package com.example.homework.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.App
import com.example.homework.R
import com.example.homework.model.manager.HomeworkManager
import com.example.homework.model.pojo.PersonNumber
import com.example.homework.ui.main.adapter.NumberAdapter

class MainFragment : Fragment() {

    private lateinit var manager: HomeworkManager

    private lateinit var getNumbers: Button
    private lateinit var loadImage: Button

    companion object {
        fun newInstance() = MainFragment()
        const val REQUEST_CODE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        manager = App.getManager(context!!) as HomeworkManager

        if (!checkContactsPermission()) {
            askPermission(Manifest.permission.READ_CONTACTS)
        }

        getNumbers = view.findViewById(R.id.getNumbersBtn)
        getNumbers.setOnClickListener {

            if (manager.numbers.size != 0) {
                val numbersRecycler = view!!.findViewById<RecyclerView>(R.id.numbers_recycler)
                numbersRecycler.layoutManager = LinearLayoutManager(activity)

                val adapter = NumberAdapter(manager.numbers)
                numbersRecycler.adapter = adapter
            } else {
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
            }
        }

        loadImage = view.findViewById(R.id.uploadImageBtn)
        loadImage.setOnClickListener {
            loadNumbers()
        }
        return view
    }

    private fun askPermission(vararg permissions: String) {
        requestPermissions(permissions, REQUEST_CODE)
    }

    private fun checkContactsPermission(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS
        )
    }

    private fun loadNumbers() {
        val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val cursor: Cursor? = context?.contentResolver?.query(uri, null, null, null, null)

        if (cursor != null) {
            while (cursor.moveToNext()) {
                val name: String =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                manager.addNumber(PersonNumber(name = name, number = number))
            }
        }

        cursor?.close()
    }
}