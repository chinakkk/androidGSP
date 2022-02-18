package com.example.empty

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        seekbar_xmin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            @SuppressLint("SetTextI18n")
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tv_xmin.text = "xMin = ${seekBar?.progress.toString()}"
            }
        })

        seekbar_xmax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            @SuppressLint("SetTextI18n")
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tv_xmax.text = "xMax = ${seekBar?.progress.toString()}"
            }
        })

        seekbar_ymin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            @SuppressLint("SetTextI18n")
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tv_ymin.text = "yMin = ${seekBar?.progress.toString()}"
            }
        })
        seekbar_ymax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            @SuppressLint("SetTextI18n")
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tv_ymax.text = "yMax = ${seekBar?.progress.toString()}"
            }
        })

        btn1.setOnClickListener {
            if(seekbar_xmin.progress.toDouble() >= seekbar_xmax.progress.toDouble()
                || seekbar_ymin.progress.toDouble() >= seekbar_ymax.progress.toDouble()){
                Toast.makeText(this,"Неккоректный ввод отрезков",Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("xMin", seekbar_xmin.progress.toDouble())
                intent.putExtra("xMax", seekbar_xmax.progress.toDouble())
                intent.putExtra("yMin", seekbar_ymin.progress.toDouble())
                intent.putExtra("yMax", seekbar_ymax.progress.toDouble())
                intent.putExtra("color_osey", color_osey.checkedRadioButtonId)
                intent.putExtra("color_graph", color_graph.checkedRadioButtonId)
                intent.putExtra("size_line", size_line.checkedRadioButtonId)
                intent.putExtra("graph", switch_graph.isChecked)
                startActivity(intent)
            }
        }
    }
}
