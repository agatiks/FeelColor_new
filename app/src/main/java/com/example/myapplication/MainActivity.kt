package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var question: ColoredObject
    private lateinit var answer: ColoredObject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        start()
        binding.redSeekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                answer.setR(progress)
                setChangedInfo(answer)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        binding.greenSeekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                answer.setG(progress)
                setChangedInfo(answer)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        binding.blueSeekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                answer.setB(progress)
                setChangedInfo(answer)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        binding.check.setOnClickListener {
            val res = question == answer
            val dialog = CheckDialogFragment(question, answer)
            val manager = supportFragmentManager
            dialog.show(manager, "checkDialog") //todo: create key
        }
    }

    private fun setChangedInfo(answer: ColoredObject) {
        binding.tr.text = answer.getR().toString()
        binding.tg.text = answer.getG().toString()
        binding.tb.text = answer.getB().toString()
        binding.argb.text = answer.toString()
        binding.answerView.setColorFilter(answer.getColor())
    }

    fun start() {
        question = ColoredObject()
        answer = ColoredObject(0, 0, 0)
        binding.questionView.setColorFilter(question.getColor())
        binding.answerView.setColorFilter(answer.getColor())
    }
}