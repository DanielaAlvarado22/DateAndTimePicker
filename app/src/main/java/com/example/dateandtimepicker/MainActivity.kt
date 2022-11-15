package com.example.dateandtimepicker

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var btnFecha : Button
    private lateinit var btnHora : Button
    private lateinit var txtFecha :TextView
    private lateinit var txtHora: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFecha = findViewById(R.id.btnFecha)
        btnHora = findViewById(R.id.btnHora)
        txtFecha = findViewById(R.id.txtFecha)
        txtHora = findViewById(R.id.txtHora)

        val Calendario = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            Calendario.set(Calendar.YEAR,year)
            Calendario.set(Calendar.MONTH,month)
            Calendario.set(Calendar.DAY_OF_MONTH,day)
            updateLable(Calendario)
        }

        btnFecha.setOnClickListener {
            DatePickerDialog(this,datePicker,Calendario.get(Calendar.YEAR),Calendario.get(Calendar.MONTH),Calendario.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnHora.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hora, minuto ->
                cal.set(Calendar.HOUR_OF_DAY,hora)
                cal.set(Calendar.MINUTE,minuto)
                txtHora.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this,timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }
    }

    private fun updateLable(Calendario: Calendar) {
        val formato = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(formato,Locale.US)
        txtFecha.setText(sdf.format(Calendario.time))
    }
}