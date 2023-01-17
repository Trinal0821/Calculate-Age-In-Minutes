package com.example.myfirstapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null;
    private var age : TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker : Button = findViewById(R.id.button)
        age = findViewById(R.id.title6)
        selectedDate = findViewById(R.id.title4)
        datePicker.setOnClickListener{
           clickDatePicker()
        }
    }

    fun clickDatePicker() {

        //Create a calendar and get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        //Open the date picker dialog
       val dpd =  DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{ view, selectedYear, selectedMonth, selectedDay ->

                //Set the date
                val date = "${selectedMonth+1}/$selectedDay/$selectedYear"
                selectedDate?.text = date

                //Calculate and set the age
                val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(date)
                theDate?.let{
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                   currentDate?.let{
                       val currentDateInMinutes = currentDate.time / 60000
                       val difference = currentDateInMinutes - selectedDateInMinutes

                       age?.text = difference.toString()
                   }

                }

            },
            year, month, day)

        //There are 86400000 in a day so you will want to subtract it from the current time
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}