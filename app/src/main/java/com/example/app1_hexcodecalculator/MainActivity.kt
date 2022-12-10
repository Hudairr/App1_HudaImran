package com.example.app1_hexcodecalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.SeekBar
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class MainActivity : AppCompatActivity(), OnSeekBarChangeListener {
    //Initialising variables for all design elements
    private lateinit var vColor: View
    private lateinit var tvCode: TextView
    private lateinit var tvValue: TextView
    private lateinit var sbRed: SeekBar
    private lateinit var sbGreen: SeekBar
    private lateinit var sbBlue: SeekBar
    private lateinit var b1: Button
    private lateinit var b2: Button
    //Initialising variables for RGB
    var red = 0
    var green = 0
    var blue = 0
        //First Function
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            //Assigning variables to relevant ids
            vColor = findViewById(R.id.v_color)
            tvCode = findViewById(R.id.tv_code)
            tvValue = findViewById(R.id.tv_value)
            sbRed = findViewById(R.id.sb_red)
            sbGreen = findViewById(R.id.sb_green)
            sbBlue = findViewById(R.id.sb_blue)
            b1 = findViewById(R.id.button)
            b2 = findViewById(R.id.button2)
            //Modal View
            val bottomSheetFragment = BottomSheetFragment()
            //Modal View to show instructions
            b2.setOnClickListener{
                bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialog")
            }
            //For functionality of SeekBars
            sbRed.setOnSeekBarChangeListener(this)
            sbGreen.setOnSeekBarChangeListener(this)
            sbBlue.setOnSeekBarChangeListener(this)
        }
        //Second Function for the RGB SeekBars
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            //use switch condition
            when (seekBar.id) {
                R.id.sb_red ->                 //When click or drag red seek bar
                    //Set red color
                    red = i
                R.id.sb_green ->                 //When click or drag green seek bar
                    //Set green color
                    green = i
                R.id.sb_blue ->                 //When click or drag blue seek bar
                    //Set blue color
                    blue = i
            }
            //Set bg color
            vColor!!.setBackgroundColor(Color.rgb(red, green, blue))
            //Set value on text view
            tvValue!!.text = String.format("RGB (%d, %d, %d)", red, green, blue)
            b1.setOnClickListener {
                //Initialize string Hex Code
                val code = hexCode(red, green, blue)
                //Set code on text view
                tvCode!!.text = code.uppercase(Locale.getDefault())
            }
        }
        //Third Function to calculate the Hex Code of the given RGB Sequence
        private fun hexCode(red: Int, green: Int, blue: Int): String {
            //Initialize string code
            var code: String?
            //Add hash tag
            code = "#"
            //Add red color
            code += Integer.toHexString(red)
            //Add green color
            code += Integer.toHexString(green)
            //Add blue color
            code += Integer.toHexString(blue)
            //Return code
            return code
        }
        //Fourth & Fifth Functions to solve errors
        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }